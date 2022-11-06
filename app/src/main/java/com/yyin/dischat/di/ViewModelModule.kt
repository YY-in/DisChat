package com.yyin.dischat.di

import android.app.Application
import com.yyin.dischat.domain.repository.DisChatAuthRepository
import com.yyin.dischat.domain.repository.PictureRepository
import com.yyin.dischat.viewmodel.UserManageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    fun provideUserManageViewModel(
        application: Application,
        authRepository: DisChatAuthRepository,
        pictureRepository: PictureRepository
    ): UserManageViewModel {
        return UserManageViewModel(
            application= application,
            authRepository = authRepository,
            pictureRepository = pictureRepository
        )
    }

    viewModelOf(::provideUserManageViewModel)
}