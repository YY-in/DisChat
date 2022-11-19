package com.yyin.dischat.rest.service

import com.google.common.base.Verify
import com.google.common.net.HttpHeaders
import com.yyin.dischat.BuildConfig
import com.yyin.dischat.rest.body.auth.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface DisChatAuthService {
    suspend fun loginByPhone(body: LoginPhoneBody) : HttpResponse
    suspend fun loginByEmail(body: LoginEmailBody) : HttpResponse
    suspend fun register(body: RegisterBody) : HttpResponse
    suspend fun authenticate(token :String): HttpResponse
    suspend fun sendPhoneCode(phone:String) : HttpResponse
    suspend fun verifyCodeByPhone(body:VerifyPhoneBody) : HttpResponse
}

class DisChatAuthServiceImpl(
    private val client: HttpClient
) : DisChatAuthService{

    override suspend fun loginByPhone(body: LoginPhoneBody): HttpResponse {
        val url = getLoginUrl() + "/phone"
        return withContext(Dispatchers.IO){
            client.post(url){ setBody(body) }
        }
    }

    override suspend fun loginByEmail(body: LoginEmailBody): HttpResponse {
        val url = getLoginUrl() + "/email"
        return withContext(Dispatchers.IO){
            client.post(url){ setBody(body) }
        }
    }

    override suspend fun register(body: RegisterBody): HttpResponse {
        val url =  getRegisterUrl()
        return   withContext(Dispatchers.IO) {
            client.post(url) {
                setBody(body)
            }
        }
    }

    override suspend fun authenticate(token: String) :  HttpResponse{
        var url = getAuthenticateUrl()
        return  withContext(Dispatchers.IO) {
            client.get(url){
                headers{
                    append(HttpHeaders.AUTHORIZATION,token)
                 }
            }
        }
    }

    override suspend fun sendPhoneCode(phone: String): HttpResponse {
        var url = getVerifyCodeUrl() + "/phone/$phone"
        return withContext(Dispatchers.IO){
            client.get(url)
        }
    }

    override suspend fun verifyCodeByPhone(body: VerifyPhoneBody): HttpResponse {
        var url = getVerifyCodeUrl() + "/verify"
        return withContext(Dispatchers.IO){
            client.get(url){
                setBody(body)
            }
        }
    }


    private  companion object {
//        const val BASE = BuildConfig.URL_API
        const val BASE = "http://192.168.43.101:8080"
        fun getLoginUrl():String{
            return "$BASE/users/login"
        }
        fun getRegisterUrl():String{
            return "$BASE/users/register"
        }
        fun getAuthenticateUrl():String{
            return "$BASE/users/authenticate"
        }
        fun getVerifyCodeUrl():String{
            return "$BASE/verification"
        }

    }

}