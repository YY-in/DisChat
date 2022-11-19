package com.yyin.dischat.di

import android.content.SharedPreferences
import com.qiniu.android.storage.UploadManager
import com.yyin.dischat.domain.manager.AccountManager
import com.yyin.dischat.domain.repository.*
import com.yyin.dischat.rest.service.DisChatApiService
import com.yyin.dischat.rest.service.DisChatAuthService
import com.yyin.dischat.rest.service.PictureService
import org.koin.dsl.module

val repositoryModule = module {

    fun provideDisChatAuthRepository(
        service : DisChatAuthService,
        accountManager: AccountManager
    ): DisChatAuthRepository {
        return DisChatAuthRepositoryImpl(
            service = service,
            accountManager= accountManager
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

    fun provideDisChatApiRepository(
        service: DisChatApiService
    ): DisChatApiRepository {
        return DisChatApiRepositoryImpl(
            service = service
        )
    }

    single { providePictureRepository(get(),get()) }
    single { provideDisChatAuthRepository(get(),get()) }
    single { provideDisChatApiRepository(get()) }
}