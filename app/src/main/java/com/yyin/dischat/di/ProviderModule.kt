package com.yyin.dischat.di

import com.yyin.dischat.domain.provider.AnonymousTelemetryProvider
import com.yyin.dischat.domain.provider.PropertyProvider
import com.yyin.dischat.domain.provider.PropertyProviderImpl
import com.yyin.dischat.domain.provider.TelemetryProvider
import org.koin.dsl.module

val providerModule = module {

    fun provideAnonymousTelemetryProvider(): TelemetryProvider {
        return AnonymousTelemetryProvider()
    }

    fun providePropertyProvider(
        telemetryProvider: TelemetryProvider
    ): PropertyProvider {
        return PropertyProviderImpl(
            telemetryProvider = telemetryProvider
        )
    }

    single { provideAnonymousTelemetryProvider() }
    single { providePropertyProvider(get()) }
}