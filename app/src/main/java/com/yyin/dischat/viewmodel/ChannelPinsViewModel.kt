package com.yyin.dischat.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.yyin.dischat.domain.manager.PersistentDataManager
import com.yyin.dischat.domain.model.DomainMessage
import com.yyin.dischat.domain.repository.DisChatApiRepository
import kotlinx.coroutines.launch

class ChannelPinsViewModel(
    persistentDataManager: PersistentDataManager,
    private val repository: DisChatApiRepository

) : BasePersistenceViewModel(persistentDataManager) {

    sealed interface State {
        object Loading : State
        object Loaded : State
        object Error : State
    }

    var state by mutableStateOf<State>(State.Loading)

    val pins = mutableStateMapOf<Long, DomainMessage>()

    fun load() {
        viewModelScope.launch {
            try {
                state = State.Loading
                val pinnedMessages = repository.getChannelPins(persistentChannelId)
                pins.clear()
                pins.putAll(pinnedMessages)
                state = State.Loaded
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}