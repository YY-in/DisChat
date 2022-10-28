package com.yyin.dischat.ui.component.user_manage.register

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.yyin.dischat.R

@Preview
@Composable
fun PhoneRegisterBody(){
   RegisterTextField(text = stringResource(R.string.phone) , onClickCodeButton = { })
}

