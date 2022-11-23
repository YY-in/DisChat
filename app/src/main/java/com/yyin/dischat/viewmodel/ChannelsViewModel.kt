package com.yyin.dischat.viewmodel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.yyin.dischat.domain.manager.PersistentDataManager
import com.yyin.dischat.domain.mapper.toDomain
import com.yyin.dischat.domain.model.DomainChannel
import com.yyin.dischat.domain.repository.DisChatApiRepository
import com.yyin.dischat.gateway.DisChatGateway
import com.yyin.dischat.gateway.event.ChannelCreateEvent
import com.yyin.dischat.gateway.event.ChannelDeleteEvent
import com.yyin.dischat.gateway.event.ChannelUpdateEvent
import com.yyin.dischat.gateway.event.GuildUpdateEvent
import com.yyin.dischat.gateway.onEvent
import com.yyin.dischat.util.getSortedChannels
import kotlinx.coroutines.launch
class ChannelsViewModel(
    gateway: DisChatGateway,
    persistentDataManager: PersistentDataManager,
    private val repository: DisChatApiRepository
) : BasePersistenceViewModel(persistentDataManager) {

    sealed interface State {
        object Unselected : State
        object Loading : State
        object Loaded : State
        object Error : State
    }

    var state by mutableStateOf<State>(State.Loading)
        private set

    val channels = mutableStateMapOf<Long, DomainChannel>()
    var guildName by mutableStateOf("")
        private set
    var guildBannerUrl by mutableStateOf<String?>(null)
        private set
    var guildBoostLevel by mutableStateOf(0)

    var selectedChannelId by mutableStateOf(0L)
        private set
    val collapsedCategories = mutableStateListOf<Long>()

    fun load() {
        viewModelScope.launch {
            try {
                state = State.Loading
                Log.i("Guilds","当前保存的persistentGuildId：$persistentGuildId")
                val guildChannels = repository.getGuildChannels(persistentGuildId)
                val guild = repository.getGuild(persistentGuildId)
                channels.clear()
                channels.putAll(guildChannels)
                guildName = guild.name
                guildBannerUrl = guild.bannerUrl
                guildBoostLevel = guild.premiumTier
                state = State.Loaded
            } catch (e: Exception) {
                state = State.Error
                e.printStackTrace()
            }
        }
    }

    fun selectChannel(channelId: Long) {
        selectedChannelId = channelId
        persistentChannelId = channelId
    }

    fun toggleCategory(categoryId: Long) {
        if (persistentCollapsedCategories.contains(categoryId))
            addPersistentCollapseCategory(categoryId)
        else {
            removePersistentCollapseCategory(categoryId)
        }

        if (!collapsedCategories.remove(categoryId))
            collapsedCategories.add(categoryId)
    }

    fun getSortedChannels(): Map<DomainChannel.Category?, List<DomainChannel>> {
        return getSortedChannels(channels.values)
    }

    init {
        if (persistentGuildId != 0L) {
            load()
        }
        if (persistentChannelId != 0L) {
            selectedChannelId = persistentChannelId
        }
        collapsedCategories.addAll(persistentDataManager.collapsedCategories)

        gateway.onEvent<GuildUpdateEvent>({ it.data.id.value == persistentGuildId }) {
            guildName = it.data.name
            guildBannerUrl = it.data.banner
            guildBoostLevel = it.data.premiumTier
        }

        gateway.onEvent<ChannelCreateEvent>({ it.data.guildId?.value == persistentGuildId }) {
            val domainData = it.data.toDomain()
            channels[domainData.id] = domainData
        }
        gateway.onEvent<ChannelUpdateEvent>({ it.data.guildId?.value == persistentGuildId }) {
            val domainData = it.data.toDomain()
            channels[domainData.id] = domainData
        }
        gateway.onEvent<ChannelDeleteEvent>({ it.data.guildId?.value == persistentGuildId }) {
            channels.remove(it.data.id.value)
        }
    }
}
