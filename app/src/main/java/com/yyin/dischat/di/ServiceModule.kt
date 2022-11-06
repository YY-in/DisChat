package com.yyin.dischat.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import com.yyin.dischat.rest.service.DisChatAuthService
import com.yyin.dischat.rest.service.DisChatAuthServiceImpl
import com.yyin.dischat.rest.service.PictureService
import com.yyin.dischat.rest.service.PictureServiceImpl
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

    fun providePictureService(
        client: HttpClient,
    ): PictureService {
        return PictureServiceImpl(
            client = client,
        )
    }

    single { providePictureService(get()) }
    single { provideDischatAuthService(get()) }
    single { provideSharedPref(get())}
}