package com.yyin.dischat.ui.screen.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyin.dischat.R
import com.yyin.dischat.ui.component.welcome.login.LoginButton
import com.yyin.dischat.ui.component.welcome.login.LoginText
import com.yyin.dischat.ui.component.welcome.login.LoginTextField
import com.yyin.dischat.ui.component.welcome.login.TopLoginBar

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

@Composable
fun ForgetTextField() {
    var code by rememberSaveable { mutableStateOf("") }
    TextField(
        value = code,
        onValueChange = { code = it },
        maxLines = 1,
        shape = RoundedCornerShape(10),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            textColor = LocalContentColor.current.copy(alpha = ContentAlpha.high),
        ),
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(50.dp),
    )
}