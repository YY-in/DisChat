package com.yyin.dischat.ui.screen.user_manage

data class UserManageState(
    val isLoading : Boolean = false,

    val loginEmail :String? = null,
    val loginPhone:String? = null,
    val loginPassword :String ="",

    val registerEmail :String ="",
    val registerPhone : String = "",
    val registerPassword : String = "",
    val registerUsername :  String = "",
    val registerAvatarUrl : String = "https://qiniu.yyin.top/linux.png",
    val verified : Boolean = false
)
