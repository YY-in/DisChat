package com.yyin.dischat.ui.component.user_manage.register

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.yyin.dischat.R

@Composable
fun EmailRegisterBody(){
    RegisterTextField(text = stringResource(R.string.email) , onClickCodeButton = { })
}