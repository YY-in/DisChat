package com.yyin.dischat

import android.app.Application
import com.yyin.dischat.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Dischat :Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Dischat)
            modules(
                viewModelModule,
                repositoryModule,
                serviceModule,
                httpModule,
                pictureModule
            )
        }
    }
}