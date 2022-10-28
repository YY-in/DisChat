package com.yyin.dischat.di

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val httpModule = module {


    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
        }
    }

    fun provideAuthClient(
        json:Json
    ): HttpClient {
        return HttpClient(CIO){
           defaultRequest {
               header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
           }
            install(HttpRequestRetry) {
                maxRetries = 5
                retryIf { _, httpResponse ->
                    !httpResponse.status.isSuccess()
                }
                retryOnExceptionIf { _, error ->
                    error is HttpRequestTimeoutException
                }
                delayMillis { retry ->
                    retry * 1000L
                }
            }
            install(ContentNegotiation) {
                json(json)
            }
        }
    }


    single { provideJson() }
    single{ provideAuthClient(get()) }
}