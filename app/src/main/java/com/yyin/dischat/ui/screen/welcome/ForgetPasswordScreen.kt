package com.yyin.dischat.ui.screen.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyin.dischat.ui.component.welcome.ConfirmButton
import com.yyin.dischat.ui.component.welcome.ForgetPasswordText
import com.yyin.dischat.ui.component.welcome.TopForgetBar


@Preview
@Composable
fun PreviewForgetPasswordScreen() {
    ForgetPasswordScreen()
}

@Composable
fun ForgetPasswordScreen() {
    Scaffold(
        topBar = { TopForgetBar() },

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
                ForgetPasswordText()

                ForgetTextField()

                ConfirmButton()
            }
        }
    }
}







