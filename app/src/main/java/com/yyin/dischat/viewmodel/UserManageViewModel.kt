package com.yyin.dischat.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyin.dischat.domain.repository.AuthResult
import com.yyin.dischat.domain.repository.DisChatAuthRepository
import com.yyin.dischat.rest.body.auth.LoginBody
import com.yyin.dischat.rest.body.auth.RegisterBody
import com.yyin.dischat.gateway.event.UserManageEvent
import com.yyin.dischat.ui.screen.user_manage.UserManageState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class UserManageViewModel(
    private val repository: DisChatAuthRepository,
) : ViewModel(){

    var state by mutableStateOf(UserManageState())

    // the channel is made to send some event back to UI
    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResult = resultChannel.receiveAsFlow()

    // init block is used to initialize the state when the viewmodel is created
    init {
       authenticate()
    }

    fun onEvent(event: UserManageEvent){
        when(event){
            is UserManageEvent.LoginAccountChange -> {
                state = state.copy(loginAccount = event.value)
            }
            is UserManageEvent.LoginPasswordChange -> {
                state = state.copy(loginPassword = event.value)
            }
            is UserManageEvent.RegisterEmailChange -> {
                state = state.copy(registerEmail = event.value)
            }
            is UserManageEvent.RegisterPhoneChange -> {
                state = state.copy(registerPhone = event.value)
            }
            is UserManageEvent.RegisterPasswordChange -> {
                state = state.copy(registerPassword = event.value)
            }


            is UserManageEvent.Login -> {
                login()
            }
            is UserManageEvent.Register -> {
                register()
            }

        }
    }

    private fun register(){
        viewModelScope.launch {
            state  = state.copy(isLoading = true)
            val result = repository.register(
                RegisterBody(
                    username = state.registerEmail,
                    password = state.registerPassword,
                    email = state.registerEmail,
                    phone = state.registerPhone,
                    avatarUrl = state.registerAvatarUrl,
                    verified = false
                )
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }

    private fun login(){
        viewModelScope.launch {
            state  = state.copy(isLoading = true)
            val result = repository.login(
                LoginBody(
                    password = state.registerPassword,
                    email = state.registerEmail,
                    phone = state.registerPhone,
                )
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }
    private fun authenticate(){
        viewModelScope.launch {
            state  = state.copy(isLoading = true)
            val result = repository.authenticate()
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }
}