package com.yyin.dischat.gateway.event

sealed class UserManageEvent{
    data class LoginAccountChange(val value: String) : UserManageEvent()
    data class LoginPasswordChange(val value: String) : UserManageEvent()
    object Login : UserManageEvent()

    data class RegisterAccountChange(val value: String) : UserManageEvent()
    data class RegisterUsernameChange(val value: String) : UserManageEvent()
    data class RegisterPasswordChange(val value: String) : UserManageEvent()
    data class RegisterAvatarChange(val value: String) : UserManageEvent()
    data class RegisterCodeChange(val value: String) : UserManageEvent()
    object UploadImg : UserManageEvent()
    object Register : UserManageEvent()
    object SendCode : UserManageEvent()
    object VerifyCode : UserManageEvent()
}
