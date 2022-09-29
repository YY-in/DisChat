package com.yyin.dischat.ui.screen.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.yyin.dischat.R
import com.yyin.dischat.ui.theme.DarkColor
import com.yyin.dischat.ui.welcome.login.LoginButton
import com.yyin.dischat.ui.welcome.login.LoginText
import com.yyin.dischat.ui.welcome.login.LoginTextField
import com.yyin.dischat.ui.welcome.login.TopLoginBar
@Preview
@Composable
fun LoginScreen(){
    Scaffold(
        topBar = { TopLoginBar() },
        backgroundColor = DarkColor
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            LoginText()

            LoginTextField(
                title = "账户信息",
                stringResource(id = R.string.loginHint_zh),
                {/*TODO*/ },
                Modifier
            )

            LoginButton()
        }

    }
}