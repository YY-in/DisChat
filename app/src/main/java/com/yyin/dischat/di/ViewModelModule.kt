package com.yyin.dischat.di

import android.app.Application
import com.yyin.dischat.domain.repository.DisChatAuthRepository
import com.yyin.dischat.viewmodel.UserManageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    fun provideUserManageViewModel(
        application: Application,
        repository: DisChatAuthRepository
    ): UserManageViewModel {
        return UserManageViewModel(
            application= application,
            repository = repository
        )
    }

    viewModelOf(::provideUserManageViewModel)
}