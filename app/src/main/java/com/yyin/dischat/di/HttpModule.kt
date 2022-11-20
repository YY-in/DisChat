package com.yyin.dischat.di

import com.yyin.dischat.BuildConfig
import com.yyin.dischat.domain.manager.AccountManager
import com.yyin.dischat.domain.provider.PropertyProvider
import com.yyin.dischat.domain.provider.TelemetryProvider
import com.yyin.dischat.util.Logger
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

val httpModule = module {


    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
        }
    }
    fun <T : HttpClientEngineConfig> HttpClientConfig<T>.installLogging(loggerDI: Logger) {
        install(Logging) {
            level = LogLevel.BODY
            logger = object : io.ktor.client.plugins.logging.Logger {
                override fun log(message: String) {
                    loggerDI.debug("HTTP", message)
                }
            }
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
    fun provideApiClient(
        json: Json,
        logger: Logger,
        accountManager: AccountManager,
        telemetryProvider: TelemetryProvider,
        propertyProvider: PropertyProvider
    ): HttpClient {
        val superProperties = json.encodeToString(propertyProvider.xSuperProperties).encodeBase64()
        return HttpClient(CIO) {
            defaultRequest {
                header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                header(HttpHeaders.Authorization, accountManager.currentAccountToken)
                header(HttpHeaders.UserAgent, telemetryProvider.userAgent)
                header(HttpHeaders.AcceptLanguage, "en-US")
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
                    retry * 2000L
                }
            }
            install(ContentNegotiation) {
                json(json)
            }
            if (BuildConfig.DEBUG)
                installLogging(logger)
        }
    }

    fun provideGatewayClient(): HttpClient {
        return HttpClient(CIO) {
            install(WebSockets) {
                maxFrameSize = Long.MAX_VALUE
            }
        }
    }


    single { provideJson() }
    single{ provideAuthClient(get()) }
    single(named("api")) { provideApiClient(get(), get(), get(), get(), get()) }
    single(named("gateway")) { provideGatewayClient() }
}