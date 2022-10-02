package com.yyin.dischat.viewmodel

import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ChatViewModel(

):ViewModel(){
    sealed interface State{
        object Unselected:State
        object Loading:State
        object Loaded:State
        object Error:State
    }
    var channelName by mutableStateOf("")
        private set
}