package com.yyin.dischat.ui.screen.user_manage

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyin.dischat.ui.component.user_manage.*

@Preview
@Composable
fun PreviewChangePasswordScreen() {
    ChangePWScreen()
}

@Composable
fun ChangePWScreen() {
    Scaffold(
        topBar = { TopChangeBar() },

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
                ChangePasswordText()

                ChangePasswordTextField()

                ChangeButtons()
            }
        }
    }
}

