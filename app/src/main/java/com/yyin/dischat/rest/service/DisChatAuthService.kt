package com.yyin.dischat.rest.service

import com.google.common.net.HttpHeaders
import com.yyin.dischat.BuildConfig
import com.yyin.dischat.rest.body.auth.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface DisChatAuthService {
    suspend fun login(body: LoginBody) : HttpResponse
    suspend fun register(body: RegisterBody) : HttpResponse
    suspend fun authenticate(token :String): HttpResponse
}

class DisChatAuthServiceImpl(
    private val client: HttpClient
) : DisChatAuthService{

    override suspend fun login(body: LoginBody): HttpResponse {
        val url = getLoginUrl()
        return withContext(Dispatchers.IO){
            client.post(url){ setBody(body) }
        }
    }

    override suspend fun register(body: RegisterBody): HttpResponse {
        val url =  getRegisterUrl()
        return  client.post(url){
            setBody(body)
        }
    }

    override suspend fun authenticate(token: String) :  HttpResponse{
        var url = getAuthenticate()
        return client.get(url){
            headers{
                append(HttpHeaders.AUTHORIZATION,token)
            }
        }
    }


    private  companion object {
        const val BASE = BuildConfig.URL_API

        fun getLoginUrl():String{
            return "$BASE/auth/login"
        }
        fun getRegisterUrl():String{
            return "$BASE/auth/register"
        }
        fun getAuthenticate():String{
            return "$BASE/auth/authenticate"
        }

    }

}