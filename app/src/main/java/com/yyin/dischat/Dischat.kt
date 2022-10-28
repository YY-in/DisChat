package com.yyin.dischat

import android.app.Application
import com.yyin.dischat.di.httpModule
import com.yyin.dischat.di.repositoryModule
import com.yyin.dischat.di.serviceModule
import com.yyin.dischat.di.viewModelModule
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
                httpModule
            )
        }
    }
}