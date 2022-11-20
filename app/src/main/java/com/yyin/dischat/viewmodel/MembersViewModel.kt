package com.yyin.dischat.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.yyin.dischat.domain.manager.PersistentDataManager
import com.yyin.dischat.domain.mapper.toDomain
import com.yyin.dischat.domain.model.DomainGuildMember
import com.yyin.dischat.domain.repository.DisChatApiRepository
import com.yyin.dischat.gateway.DisChatGateway
import com.yyin.dischat.gateway.event.GuildMemberChunkEvent
import com.yyin.dischat.gateway.onEvent
import com.yyin.dischat.gateway.scheduleOnConnection
import kotlinx.coroutines.launch

class MembersViewModel(
    persistentDataManager: PersistentDataManager,
    private val gateway: DisChatGateway,
    private val repository: DisChatApiRepository,
) : BasePersistenceViewModel(persistentDataManager) {

    val members = mutableStateListOf<DomainGuildMember>()

    fun load() {
        viewModelScope.launch {
            gateway.requestGuildMembers(persistentGuildId)
        }
    }

    init {
        gateway.onEvent<GuildMemberChunkEvent> {
            val domainMembers = it.data.toDomain().guildMembers
            members.addAll(domainMembers)
        }

        if (persistentGuildId != 0L) {
            gateway.scheduleOnConnection {
                load()
            }
        }
    }
}