package com.yyin.dischat.gateway.event

sealed class UserManageEvent{
    data class LoginAccountChange(val value: String) : UserManageEvent()
    data class LoginPasswordChange(val value: String) : UserManageEvent()
    object Login : UserManageEvent()

    data class RegisterEmailChange(val value: String) : UserManageEvent()
    data class RegisterPhoneChange(val value: String) : UserManageEvent()
    data class RegisterPasswordChange(val value: String) : UserManageEvent()
    object Register : UserManageEvent()
}
