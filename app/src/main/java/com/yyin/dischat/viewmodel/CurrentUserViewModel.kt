package com.yyin.dischat.viewmodel

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xinto.opencord.gateway.event.SessionsReplaceEvent
import com.xinto.partialgen.PartialValue
import com.yyin.dischat.R
import com.yyin.dischat.domain.manager.CacheManager
import com.yyin.dischat.domain.mapper.toApi
import com.yyin.dischat.domain.mapper.toDomain
import com.yyin.dischat.domain.model.*
import com.yyin.dischat.domain.repository.DisChatApiRepository
import com.yyin.dischat.gateway.DisChatGateway
import com.yyin.dischat.gateway.dto.UpdatePresence
import com.yyin.dischat.gateway.event.ReadyEvent
import com.yyin.dischat.gateway.event.UserSettingsUpdateEvent
import com.yyin.dischat.gateway.event.UserUpdateEvent
import com.yyin.dischat.gateway.onEvent
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock


class CurrentUserViewModel(
    val repository: DisChatApiRepository,
    val gateway: DisChatGateway,
    val cache: CacheManager,
) : ViewModel() {

    sealed interface State {
        object Loading : State
        object Loaded : State
        object Error : State
    }

    var state by mutableStateOf<State>(State.Loading)
        private set

    var avatarUrl by mutableStateOf("")
        private set
    var username by mutableStateOf("")
        private set
    var discriminator by mutableStateOf("")
        private set

    var userStatus by mutableStateOf<DomainUserStatus?>(null)
        private set
    var userCustomStatus by mutableStateOf<DomainCustomStatus?>(null)
        private set
    var isStreaming by mutableStateOf(false)
        private set

    private var userSettings: DomainUserSettings? = null

    fun setStatus(@DrawableRes icon: Int) {
        viewModelScope.launch {
            val status = when (icon) {
                R.drawable.ic_status_online -> DomainUserStatus.Online
                R.drawable.ic_status_idle -> DomainUserStatus.Idle
                R.drawable.ic_status_dnd -> DomainUserStatus.Dnd
                R.drawable.ic_status_invisible -> DomainUserStatus.Invisible
                else -> throw IllegalStateException("Unknown status icon!")
            }

            gateway.updatePresence(
                UpdatePresence(
                    status = status.value,
                    afk = null,
                    since = Clock.System.now().toEpochMilliseconds(),
                    activities = cache.getActivities().map { it.toApi() },
                )
            )

            val settings = DomainUserSettingsPartial(status = PartialValue.Value(status))
            repository.updateUserSettings(settings)
        }
    }

    fun setCustomStatus(status: DomainCustomStatus?) {
        viewModelScope.launch {
            val settings = DomainUserSettingsPartial(
                customStatus = PartialValue.Value(status)
            )
            repository.updateUserSettings(settings)

            val currentMillis = Clock.System.now().toEpochMilliseconds()
            val activities = cache.getActivities()
                .filter { it !is DomainActivityCustom }
                .toMutableList()

            if (status != null) {
                activities += DomainActivityCustom(
                    name = "Custom Status",
                    status = status.text,
                    createdAt = currentMillis,
                    emoji = if (status.emojiId == null || status.emojiName == null) null else {
                        DomainActivityEmoji(
                            name = status.emojiName,
                            id = status.emojiId,
                            animated = false, // TODO: fix this
                        )
                    }
                )
            }

            gateway.updatePresence(
                UpdatePresence(
                    status = cache.getCurrentSession().status,
                    since = currentMillis,
                    afk = null,
                    activities = activities.map { it.toApi() },
                )
            )
        }
    }

    init {
        gateway.onEvent<ReadyEvent> {
            val domainUser = it.data.user.toDomain()
            avatarUrl = domainUser.avatarUrl
            username = domainUser.username
            discriminator = domainUser.formattedDiscriminator
        }
        gateway.onEvent<UserUpdateEvent> {
            val data = it.data.toDomain() as DomainUserPrivate
            avatarUrl = data.avatarUrl
            username = data.username
            discriminator = data.formattedDiscriminator
        }
        gateway.onEvent<UserSettingsUpdateEvent> {
            val mergedData = userSettings?.merge(it.data.toDomain())
                .also { mergedData -> userSettings = mergedData }
            userStatus = mergedData?.status
            userCustomStatus = mergedData?.customStatus
        }
        gateway.onEvent<SessionsReplaceEvent> {
            isStreaming = cache.getActivities()
                .any { it is DomainActivityStreaming }
        }

        viewModelScope.launch {
            try {
                val settings = repository.getUserSettings()
                userSettings = settings
                userStatus = settings.status
                userCustomStatus = settings.customStatus
                state = State.Loaded
            } catch (e: Throwable) {
                e.printStackTrace()
                state = State.Error
            }
        }
    }
}
