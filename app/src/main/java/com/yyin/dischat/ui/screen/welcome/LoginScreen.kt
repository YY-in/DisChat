package com.yyin.dischat.ui.screen.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyin.dischat.ui.theme.DarkColor
import com.yyin.dischat.ui.component.login.LoginButton
import com.yyin.dischat.ui.component.login.LoginText
import com.yyin.dischat.ui.component.login.LoginTextField
import com.yyin.dischat.ui.component.login.TopLoginBar

@Preview
@Composable
fun LoginScreen(){
    Scaffold(
        topBar = { TopLoginBar() },

    ) {paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            tonalElevation = 2.dp
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                LoginText()

                LoginTextField()

                LoginButton()
            }
        }
    }
}