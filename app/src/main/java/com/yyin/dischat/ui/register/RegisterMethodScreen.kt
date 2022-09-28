package com.yyin.dischat.ui.register

import androidx.compose.runtime.Composable
import com.yyin.dischat.R


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