package com.yyin.dischat.viewmodel

import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyin.dischat.domain.manager.PersistentDataManager
import com.yyin.dischat.domain.model.DomainMessage
import com.yyin.dischat.domain.repository.DisChatApiRepository
import com.yyin.dischat.util.throttle
import kotlinx.coroutines.launch

class ChatViewModel(
    persistentDataManager: PersistentDataManager,
    private val repository: DisChatApiRepository

):BasePersistenceViewModel(persistentDataManager){
    sealed interface State{
        object Unselected:State
        object Loading:State
        object Loaded:State
        object Error:State
    }
    // 状态变量的存储
    var state by mutableStateOf<State>(State.Unselected)
        private set
    // 消息的存储
    val messages = mutableStateMapOf<Long, DomainMessage>()

    var channelName by mutableStateOf("")
        private set
    var userMessage by mutableStateOf("")
        private set
    var sendEnabled by mutableStateOf(true)
        private set
    var currentUserId by mutableStateOf<Long?>(null)
        private set

    // throttle 阀门、节流阀
    val startTyping = throttle(9500, viewModelScope) {
        repository.startTyping(persistentChannelId)
    }


    fun load() {
        viewModelScope.launch {
            try {
                state = State.Loading
                val channelMessages = repository.getChannelMessages(persistentChannelId)
                val channel = repository.getChannel(persistentChannelId)
                messages.clear()
                messages.putAll(channelMessages)
                channelName = channel.name
                state = State.Loaded
            } catch (e: Exception) {
                state = State.Error
                e.printStackTrace()
            }
        }
    }

    fun updateMessage(message: String) {
        userMessage = message
        startTyping()
    }

    fun sendMessage() {
        viewModelScope.launch {
            sendEnabled = false
            val message = userMessage
            userMessage = ""
//            repository.postChannelMessage(
//                channelId = persistentChannelId,
//                MessageBody(
//                    content = message
//               )
//            )
            sendEnabled = true
        }
    }

    fun getSortedMessages(): List<DomainMessage> {
        return messages.values.sortedByDescending { it.id }
    }
}