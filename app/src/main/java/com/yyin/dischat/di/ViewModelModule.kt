package com.yyin.dischat.di

import org.koin.androidx.viewmodel.dsl.viewModel
import com.yyin.dischat.domain.repository.DisChatAuthRepository
import com.yyin.dischat.viewmodel.UserManageViewModel
import org.koin.dsl.module

val viewModelModule = module {

    fun provideUserManageViewModel(
        repository: DisChatAuthRepository
    ): UserManageViewModel {
        return UserManageViewModel(
            repository = repository
        )
    }

    viewModel {
        provideUserManageViewModel(get())
    }
}