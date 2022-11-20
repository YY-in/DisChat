package com.yyin.dischat.di

import android.app.Application
import com.yyin.dischat.domain.manager.CacheManager
import com.yyin.dischat.domain.manager.PersistentDataManager
import com.yyin.dischat.domain.repository.DisChatApiRepository
import com.yyin.dischat.domain.repository.DisChatAuthRepository
import com.yyin.dischat.domain.repository.PictureRepository
import com.yyin.dischat.gateway.DisChatGateway
import com.yyin.dischat.viewmodel.*
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

    fun provideChannelsViewModel(
        gateway: DisChatGateway,
        repository: DisChatApiRepository,
        persistentDataManager: PersistentDataManager
    ): ChannelsViewModel {
        return ChannelsViewModel(
            gateway = gateway,
            repository = repository,
            persistentDataManager = persistentDataManager
        )
    }

    fun provideChatViewModel(
        repository: DisChatApiRepository,
        persistentDataManager: PersistentDataManager
    ): ChatViewModel {
        return ChatViewModel(
            repository = repository,
            persistentDataManager = persistentDataManager
        )
    }
    fun provideGuildsViewModel(
        repository: DisChatApiRepository,
        persistentDataManager: PersistentDataManager
    ): GuildsViewModel {
        return GuildsViewModel(
            repository = repository,
            persistentDataManager = persistentDataManager
        )
    }

    fun provideCurrentUserViewModel(
        gateway: DisChatGateway,
        repository: DisChatApiRepository,
        cache: CacheManager,
    ): CurrentUserViewModel {
        return CurrentUserViewModel(
            gateway = gateway,
            repository = repository,
            cache = cache,
        )
    }
    fun provideMembersViewModel(
        persistentDataManager: PersistentDataManager,
        gateway: DisChatGateway,
        repository: DisChatApiRepository
    ): MembersViewModel {
        return MembersViewModel(
            gateway = gateway,
            persistentDataManager = persistentDataManager,
            repository = repository
        )
    }

    fun provideChannelPinsViewModel(
        persistentDataManager: PersistentDataManager,
        repository: DisChatApiRepository
    ): ChannelPinsViewModel {
        return ChannelPinsViewModel(
            persistentDataManager = persistentDataManager,
            repository = repository
        )
    }

    viewModelOf(::provideUserManageViewModel)
    viewModelOf(::provideChannelsViewModel)
    viewModelOf(::provideChatViewModel)
    viewModelOf(::provideGuildsViewModel)
    viewModelOf(::provideCurrentUserViewModel)
    viewModelOf(::provideMembersViewModel)
    viewModelOf(::provideChannelPinsViewModel)
}