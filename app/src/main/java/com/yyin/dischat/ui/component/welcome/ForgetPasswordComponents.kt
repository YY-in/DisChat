package com.yyin.dischat.ui.component.welcome

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yyin.dischat.R
import com.yyin.dischat.ui.theme.Indigo700
import com.yyin.dischat.ui.theme.White

@Composable
fun ForgetPasswordText() {
    Text(
        text = stringResource(R.string.forget_password_tip),
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.W900,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .alpha(ContentAlpha.high)
            .padding(top = 15.dp, bottom = 8.dp)
    )
    Text(
        text = stringResource(R.string.forget_password_caption),
        style = MaterialTheme.typography.caption,
        fontWeight = FontWeight.W900,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .alpha(ContentAlpha.medium)
            .padding(bottom = 15.dp)
    )
}

@Composable
fun TopForgetBar() {
    SmallTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.forget_password),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.alpha(ContentAlpha.medium)
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* TODO:doSomething() */ }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "return",
                    tint = LocalContentColor.current.copy(alpha = ContentAlpha.medium),
                )
            }
        },

        )
}

@Composable
fun ConfirmButton() {
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

        Text(stringResource(R.string.confirm))
    }
}