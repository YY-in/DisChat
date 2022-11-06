package com.yyin.dischat.di

import android.content.SharedPreferences
import com.qiniu.android.storage.UploadManager
import com.yyin.dischat.domain.repository.DisChatAuthRepository
import com.yyin.dischat.domain.repository.DisChatAuthRepositoryImpl
import com.yyin.dischat.domain.repository.PictureRepository
import com.yyin.dischat.domain.repository.PictureRepositoryImpl
import com.yyin.dischat.rest.service.DisChatAuthService
import com.yyin.dischat.rest.service.PictureService
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

    fun providePictureRepository(
        service : PictureService,
        uploaderManager: UploadManager
    ): PictureRepository {
        return PictureRepositoryImpl(
            service = service,
            uploaderManager = uploaderManager,
        )
    }

    single { providePictureRepository(get(),get()) }
    single { provideDisChatAuthRepository(get(),get()) }
}