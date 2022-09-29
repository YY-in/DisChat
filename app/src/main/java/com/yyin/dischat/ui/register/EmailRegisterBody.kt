package com.yyin.dischat.ui.register

import androidx.compose.runtime.Composable
import androidx.compose.material.Text

@Composable
fun EmailRegisterBody(){
    RegisterTextField(text = "邮箱地址" , onTextChange = { }, onClickCodeButton = { })
}