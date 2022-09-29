package com.yyin.dischat.ui.welcome.register

import androidx.compose.runtime.Composable

@Composable
fun EmailRegisterBody(){
    RegisterTextField(text = "邮箱地址" , onTextChange = { }, onClickCodeButton = { })
}