package com.yyin.dischat.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.yyin.dischat.domain.model.DomainCustomStatus
import com.yyin.dischat.domain.model.DomainUserStatus

class CurrentUserViewModel (): ViewModel(){
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

}