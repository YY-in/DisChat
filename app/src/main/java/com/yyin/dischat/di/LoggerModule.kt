package com.yyin.dischat.di

import com.yyin.dischat.util.Logger
import com.yyin.dischat.util.LoggerImpl
import org.koin.dsl.module

val loggerModule = module {

    fun provideLogger(): Logger {
        return LoggerImpl()
    }

    single { provideLogger() }
}