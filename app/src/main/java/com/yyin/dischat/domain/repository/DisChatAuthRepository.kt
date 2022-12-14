package com.yyin.dischat.domain.repository

import android.content.SharedPreferences
import android.util.Log
import com.yyin.dischat.domain.manager.AccountManager
import com.yyin.dischat.rest.body.auth.*
import com.yyin.dischat.rest.dto.ApiLogin
import com.yyin.dischat.rest.dto.BaseResponse
import com.yyin.dischat.rest.dto.Response
import com.yyin.dischat.rest.service.DisChatAuthService
import io.ktor.client.call.*
import io.ktor.client.call.body as body


sealed class AuthResult<T>(val data:T? = null){
    class Authorized<T>(data:T? = null) : AuthResult<T>(data)
    class UnAuthorized<T>: AuthResult<T>()
    class UnKnowError<T> : AuthResult<T>()
}
sealed class VerifyResult<T>(val data:T? = null){
    class Verified<T>(data:T? = null) : VerifyResult<T>(data)
    class UnVerified<T>: VerifyResult<T>()
    class WithoutAccount<T> : VerifyResult<T>()
    class UnKnowError<T> : VerifyResult<T>()
}
interface DisChatAuthRepository {
    suspend fun login(body: LoginBody): AuthResult<Unit>
    suspend fun register(body : RegisterBody):AuthResult<Unit>
    suspend fun authenticate():AuthResult<Unit>

    suspend fun sendVerifyCode(body: VerifyBody):VerifyResult<Unit>
    suspend fun verifyCode(body: VerifyBody): VerifyResult<Unit>
}

class DisChatAuthRepositoryImpl(
    private val service : DisChatAuthService,
    private  val accountManager: AccountManager
): DisChatAuthRepository{
    override suspend fun login(requestBody: LoginBody): AuthResult<Unit>{
        val Tag = "Login"
        try {
            if (requestBody.email != null){
                Log.i(Tag,"Login by email")
                val  response =  service.loginByEmail(LoginEmailBody(requestBody.email,requestBody.password))
                return when (response.status.value) {
                    200 -> {
                        val tokenResponse:Response<ApiLogin> = response.body()
                        accountManager.currentAccountToken =  tokenResponse.data.token.toString()
                        AuthResult.Authorized()
                    }
                    409 -> {
                        AuthResult.UnAuthorized()
                    }
                    else -> {
                        Log.w(Tag,"?????????????????????")
                        AuthResult.UnKnowError()
                    }
                }
            }else if (requestBody.phone != null){
                Log.i(Tag,"Login by phone")
                val  response =  service.loginByPhone(LoginPhoneBody(requestBody.phone,requestBody.password))
                return when (response.status.value) {
                    200 -> {
                        val tokenResponse:Response<ApiLogin> = response.body()
                        Log.i(Tag, tokenResponse.data.token.toString())
                        accountManager.currentAccountToken =tokenResponse.data.token.toString()
                        AuthResult.Authorized()
                    }
                    409 -> {
                        AuthResult.UnAuthorized()
                    }
                    else -> {
                        AuthResult.UnKnowError()
                    }
                }
            }else{
                Log.w(Tag,"?????????????????????")
                return AuthResult.UnKnowError()
            }

        }catch (e:Exception){
            Log.w(Tag, "????????????IP,???????????????$e\n")
            return  AuthResult.UnKnowError()
        }

    }

    override suspend fun register(requestBody: RegisterBody) : AuthResult<Unit>{
        try {
            val  response =  service.register(requestBody)
            return when (response.status.value) {
                200 -> {
                    login(
                        LoginBody(
                            requestBody.email,
                            requestBody.phone,
                            requestBody.password
                        )
                    )
                }
                400 -> {
                    AuthResult.UnAuthorized()
                }
                else -> {
                    AuthResult.UnKnowError()
                }
            }
        }catch (e:Exception){
            return  AuthResult.UnKnowError()
            Log.e("Register",e.message.toString())
        }
    }

    override suspend fun authenticate():AuthResult<Unit> {
        val Tag = "Authenticate"
        Log.i(Tag,"Authenticate")
        /*TODO: ?????????????????????*/
        Log.e(Tag,"?????????prefs?????????")
        accountManager.currentAccountToken = ""
        val token = accountManager.currentAccountToken ?: return AuthResult.UnAuthorized()
        try {
            val  response =  service.authenticate("Bearer $token")
            return when (response.status.value) {
                200 -> {
                    val response:BaseResponse = response.body()
                    accountManager.currentAccountToken=response.message
                    Log.i(Tag,"${response.message}")
                    Log.d(Tag,"Authenticate success")
                    AuthResult.Authorized()
                }
                401 -> {
                    Log.i(Tag,"Authenticate failed")
                    AuthResult.UnAuthorized()
                }
                else -> {
                    AuthResult.UnKnowError()
                }
            }
        }catch (e:Exception){
            Log.w(Tag,e.message.toString())
            return AuthResult.UnKnowError()
        }
    }

    override suspend fun sendVerifyCode(body: VerifyBody): VerifyResult<Unit> {
       val  Tag =  "SendCode"
       Log.i(Tag,"Send verify code")
       try {
            if (body.phone!=null){
                Log.i(Tag,"Send verify code by phone: ${body.phone}")
                val response = service.sendPhoneCode(phone=body.phone)
                val res: BaseResponse = response.body()
                Log.i(Tag, "${res.message}")
                return when (response.status.value) {
                    200 -> {
                        VerifyResult.UnVerified()
                    }
                    else -> {
                        VerifyResult.UnKnowError()
                    }
            }
        }else if (body.email!=null){
                Log.i(Tag,"Send verify code by email: ${body.email}")
                return VerifyResult.UnKnowError()
        }else{
            Log.w(Tag,"?????????????????????")
            return VerifyResult.UnKnowError()
        }
       }catch (e:Exception){
           Log.w(Tag,e.message.toString())
           return VerifyResult.UnKnowError()
       }
    }

    override suspend fun verifyCode(body: VerifyBody): VerifyResult<Unit> {
        val Tag = "VerifyCode"
        try {
        if (body.phone!=null&&body.code!=null){
            Log.i(Tag,"Confirm verify code by phone: ${body.phone}")
            val response = service.verifyCodeByPhone(VerifyPhoneBody(body.phone,body.code))
            val res: BaseResponse = response.body()
            Log.i(Tag, "${res.message}")
            return when (response.status.value) {
                200 -> {
                    VerifyResult.Verified()
                }
                409 -> {
                    VerifyResult.UnVerified()
                }
                404 -> {
                    VerifyResult.WithoutAccount()
                }
                else -> {
                    VerifyResult.UnKnowError()
                }
            }
        }else if (body.email!=null){
            Log.i(Tag,"Confirm verify code by email: ${body.email}")
            return VerifyResult.UnKnowError()
        }else{
            Log.w(Tag,"?????????????????????")
            return VerifyResult.UnKnowError()
        }
        }catch (e:Exception){
            Log.w(Tag,e.message.toString())
            return VerifyResult.UnKnowError()
        }
    }
}