package com.yyin.dischat.di

import com.google.android.exoplayer2.util.Log
import com.qiniu.android.storage.Configuration
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import com.yyin.dischat.gateway.event.UserManageEvent
import com.yyin.dischat.rest.dto.ApiUploadToken
import com.yyin.dischat.rest.service.PictureServiceImpl
import com.yyin.dischat.viewmodel.UserManageViewModel
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.dsl.module

var pictureModule = module {

    fun provideUploaderManager(): UploadManager {
        return UploadManager(Configuration.Builder().build())
    }

    fun provideUpCompletionHandler(
        viewModel: UserManageViewModel
    ): UpCompletionHandler {
        return UpCompletionHandler { key, info, response ->
            if (info.isOK) {
                Log.d("qiniu", "Upload Success")
                val avatarUrl = "https://qiniu.yyin.top/$key"
                viewModel.onEvent(UserManageEvent.LoginPasswordChange(avatarUrl))
            } else {
                Log.d("qiniu", "Upload Fail")
            }
            Log.d("qiniu", key + ",\r\n " + info + ",\r\n " + response)
        }
    }

    single { provideUploaderManager() }
    single { provideUpCompletionHandler(get()) }
}