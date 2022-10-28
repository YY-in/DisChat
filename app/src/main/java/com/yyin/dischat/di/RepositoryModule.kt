package com.yyin.dischat.di

import android.content.SharedPreferences
import com.yyin.dischat.domain.repository.DisChatAuthRepository
import com.yyin.dischat.domain.repository.DisChatAuthRepositoryImpl
import com.yyin.dischat.rest.service.DisChatAuthService
import org.koin.dsl.module

val repositoryModule = module {

    fun provideDisChatAuthRepository(
        service : DisChatAuthService,
        preferences: SharedPreferences
    ): DisChatAuthRepository {
        return DisChatAuthRepositoryImpl(
            service = service,
            prefs  = preferences
        )
    }

    single { provideDisChatAuthRepository(get(),get()) }
}