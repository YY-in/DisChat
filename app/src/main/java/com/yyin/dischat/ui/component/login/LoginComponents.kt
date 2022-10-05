package com.yyin.dischat.ui.component.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yyin.dischat.R
import com.yyin.dischat.ui.theme.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color


@Composable
fun LoginButton() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Indigo700,
            contentColor = White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {

        Text(stringResource(R.string.login))
    }
}

@Composable
fun LoginText() {
    Text(
        text = stringResource(R.string.loginWelcome),
        style = MaterialTheme.typography.h5,
        fontWeight = W900,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .alpha(ContentAlpha.high)
            .padding(top = 15.dp, bottom = 8.dp)
    )
    Text(
        text = stringResource(R.string.loginWelcomeCaption),
        style = MaterialTheme.typography.caption,
        fontWeight = W900,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .alpha(ContentAlpha.medium)
            .padding(bottom = 15.dp)
    )
}

@Composable
fun TopLoginBar() {
    SmallTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.login),
                fontWeight = FontWeight.Bold,
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* TODO:doSomething() */ }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "return",
                )
            }
        },

        )
}


@Composable
fun LoginTextField(
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        var account by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var passwordHidden by rememberSaveable { mutableStateOf(true) }

        Text(
            text = stringResource(R.string.accountMessage),
            style = MaterialTheme.typography.caption,
            fontWeight = W500,
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = 8.dp, top = 20.dp)
        )

        TextField(
            placeholder = {
                Text(
                    stringResource(R.string.loginHint),
                    color =  LocalContentColor.current.copy(alpha = ContentAlpha.medium),
                )
            },
            value = account,
            onValueChange = { account = it },
            maxLines = 1,
            shape = RoundedCornerShape(10),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                textColor = LocalContentColor.current.copy(alpha = ContentAlpha.high),
            ),
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth()
                .height(50.dp),
        )


        TextField(
            placeholder = {
                Text(
                    stringResource(R.string.passwordHint),
                    color = LocalContentColor.current.copy(alpha = ContentAlpha.medium)
                )
            },
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            visualTransformation =
            if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { passwordHidden = !passwordHidden }) {
                    val visibilityIcon =
                        if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (passwordHidden) "显示密码" else "隐藏密码"
                    Icon(
                        imageVector = visibilityIcon,
                        contentDescription = description,
                        tint = LocalContentColor.current.copy(alpha = ContentAlpha.high)
                    )
                }
            },
            shape = RoundedCornerShape(10),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor =  MaterialTheme.colors.surface,
                textColor = LocalContentColor.current.copy(alpha = ContentAlpha.medium),
            ),
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth()
                .height(50.dp),
        )
        Text(
            text = stringResource(R.string.forgetKey),
            style = MaterialTheme.typography.caption,
            color = Teal200,
            modifier = Modifier
                .alpha(ContentAlpha.high)
                .padding(bottom = 8.dp)
        )
        Text(
            text = stringResource(R.string.useKeyManager),
            style = MaterialTheme.typography.caption,
            color = Teal200,
            modifier = Modifier
                .alpha(ContentAlpha.high)

        )


    }

}

