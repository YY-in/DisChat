package com.yyin.dischat.domain.repository

import android.content.SharedPreferences
import android.util.Log
import com.yyin.dischat.rest.body.auth.LoginBody
import com.yyin.dischat.rest.body.auth.RegisterBody
import com.yyin.dischat.rest.dto.ApiLogin
import com.yyin.dischat.rest.service.DisChatAuthService
import io.ktor.client.call.*
import java.util.concurrent.TimeoutException
import io.ktor.client.call.body as body


sealed class AuthResult<T>(val data:T? = null){
    class Authorized<T>(data:T? = null) : AuthResult<T>(data)
    class UnAuthorized<T>: AuthResult<T>()
    class UnKnowError<T> : AuthResult<T>()
}

interface DisChatAuthRepository {
    suspend fun login(body: LoginBody): AuthResult<Unit>

    suspend fun register(body : RegisterBody):AuthResult<Unit>

    suspend fun authenticate():AuthResult<Unit>
}

class DisChatAuthRepositoryImpl(
    private val service : DisChatAuthService,
    private val prefs :  SharedPreferences
): DisChatAuthRepository{
    override suspend fun login(requestBody: LoginBody): AuthResult<Unit>{
        try {
            val  response =  service.login(requestBody)
            return when (response.status.value) {
                200 -> {
                    val tokenResponse:ApiLogin = response.body()
                    Log.i("test-token", tokenResponse.token.toString())
                    prefs.edit().putString("jwt", tokenResponse.token.toString()).apply()
                    AuthResult.Authorized()
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
            Log.e("Login",e.message.toString())
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
        val token = prefs.getString("jwt",null) ?: return  AuthResult.UnAuthorized()
        try {
            val  response =  service.authenticate("Bearer $token")
            return when (response.status.value) {
                200 -> {
                    val token = response.body<String>()
                    prefs.edit().putString("token",token).apply()
                    AuthResult.Authorized()
                }
                400 -> {
                    AuthResult.UnAuthorized()
                }
                else -> {
                    AuthResult.UnKnowError()
                }
            }
        }catch (e:Exception){
            return AuthResult.UnKnowError()
            Log.e("Authenticate",e.message.toString())
        }
    }

}