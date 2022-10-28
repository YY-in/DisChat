package com.yyin.dischat.ui.component.user_manage.register

import androidx.compose.runtime.Composable


enum class RegisterMethodScreen(
    val text: String,
    private val body: @Composable ((RegisterMethodScreen)-> Unit) -> Unit
) {
    PhoneRegister(
        text = "电话号码",
        body = { PhoneRegisterBody() }
    ),
    EmailRegister(
        text = "邮箱地址",
        body = { EmailRegisterBody() }
    );

    @Composable
    fun content(onScreenChange: (RegisterMethodScreen) -> Unit) {
        body(onScreenChange)
    }
}