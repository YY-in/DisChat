package com.yyin.dischat.ui.welcome.register

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PhoneRegisterBody(){
   RegisterTextField(text = "手机号码" , onTextChange = { }, onClickCodeButton = { })
}

