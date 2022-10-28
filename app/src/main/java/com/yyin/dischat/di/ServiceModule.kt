package com.yyin.dischat.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.yyin.dischat.rest.service.DisChatAuthService
import com.yyin.dischat.rest.service.DisChatAuthServiceImpl
import io.ktor.client.*
import org.koin.dsl.module

val serviceModule = module {

    fun provideDischatAuthService(
        client: HttpClient
    ): DisChatAuthService {
        return DisChatAuthServiceImpl(
            client = client
        )
    }

    fun provideSharedPref(app :Application):SharedPreferences{
        // make sure nobody can access these  data
        return app.getSharedPreferences("prefs",MODE_PRIVATE)
    }

    single { provideDischatAuthService(get()) }
    single { provideSharedPref(get())}
}