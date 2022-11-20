package com.yyin.dischat.di


import com.yyin.dischat.domain.manager.AccountManager
import com.yyin.dischat.domain.provider.PropertyProvider
import com.yyin.dischat.gateway.DisChatGateway
import com.yyin.dischat.gateway.DisChatGatewayImpl
import com.yyin.dischat.util.Logger
import io.ktor.client.*
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

val gatewayModule = module {

    fun provideGateway(
        client: HttpClient,
        json: Json,
        accountManager: AccountManager,
        propertyProvider: PropertyProvider,
        logger: Logger
    ): DisChatGateway {
        return DisChatGatewayImpl(
            client = client,
            json = json,
            accountManager = accountManager,
            propertyProvider = propertyProvider,
            logger = logger
        )
    }

    single { provideGateway(get(named("gateway")), get(), get(), get(), get()) }
}