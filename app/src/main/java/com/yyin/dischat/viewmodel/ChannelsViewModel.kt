package com.yyin.dischat.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.yyin.dischat.domain.manager.PersistentDataManager
import com.yyin.dischat.domain.model.DomainChannel
import com.yyin.dischat.domain.repository.DisChatApiRepository
import com.yyin.dischat.util.getSortedChannels
import kotlinx.coroutines.launch

class ChannelsViewModel(
    persistentDataManager: PersistentDataManager,
    private val repository : DisChatApiRepository
) : BasePersistenceViewModel(persistentDataManager) {
    sealed interface State {
        object Unselected : State
        object Loading : State
        object Loaded : State
        object Error : State
    }
    var state by mutableStateOf<State>(State.Unselected)
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

    fun getSortedChannels(): Map<DomainChannel.Category?, List<DomainChannel>> {
        return getSortedChannels(channels.values)
    }

    fun selectChannel(channelId: Long) {
        selectedChannelId = channelId
        persistentChannelId = channelId
    }
    //打开目录开关
    fun toggleCategory(categoryId: Long) {
        if (persistentCollapsedCategories.contains(categoryId))
            addPersistentCollapseCategory(categoryId)
        else {
            removePersistentCollapseCategory(categoryId)
        }

        if (!collapsedCategories.remove(categoryId))
            collapsedCategories.add(categoryId)
    }
    fun load(){
        viewModelScope.launch {
            try {
                // 初始化加载loading
                state = State.Loading
                // 根据当前的channelId 获取相关的
                val guildChannels = repository.getGuildChannels(persistentChannelId)

            } catch (e:Exception){
                state = State.Error
                e.printStackTrace()
            }
        }
    }
}