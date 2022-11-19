package com.yyin.dischat.gateway.event

import com.yyin.dischat.gateway.event.UserManageEvent

data class UserManageState(
    val isLoading : Boolean = false,

    val loginAccount: String="",
    val loginEmail :String? = null,
    val loginPhone:String? = null,
    val loginPassword :String ="",

    val registerCode:String ="",
    val registerAccount: String="",
    val registerEmail :String ?= null,
    val registerPhone : String ?= null,
    val registerPassword : String = "",
    val registerUsername :  String = "",
    val registerAvatarUrl : String = "https://qiniu.yyin.top/linux.png",
    val verified : Boolean = false
)
