package com.yyin.dischat.login

import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyin.dischat.R
import com.yyin.dischat.ui.theme.*
import javax.security.auth.login.LoginException




@Preview
@Composable
fun LoginCard() {
    Scaffold(
        topBar = { TopLoginBar() },
        backgroundColor = DarkColor
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.loginWelcome_zh),
                style = MaterialTheme.typography.h5,
                fontWeight = W900,
                color = White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .alpha(ContentAlpha.high)
                    .padding(top = 15.dp, bottom = 8.dp)
            )
            Text(
                text = stringResource(R.string.loginWelcomeCaption_zh),
                style = MaterialTheme.typography.caption,
                fontWeight = W900,
                color = White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .padding(bottom = 15.dp)
            )

            LoginTextField(title = "账户信息", stringResource(id = R.string.loginHint_zh), {/*TODO*/}, Modifier)

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

                Text(stringResource(R.string.login_zh))
            }
        }

    }
}

@Composable
fun TopLoginBar() {
    TopAppBar(
        backgroundColor = BlueGray900,
        title = {
            Text(
                text = stringResource(R.string.login_zh),
                fontWeight = FontWeight.Bold,
                color = White,
                modifier = Modifier.alpha(ContentAlpha.medium)
            )


        },
        navigationIcon = {
            IconButton(onClick = { /* TODO:doSomething() */ }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "return",
                    tint = White.copy(alpha = ContentAlpha.medium)
                )
            }
        },

        )
}

@Composable
fun LoginTextField(
    title: String,
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.overline,
            fontWeight = W500,
            color = White,
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = 8.dp, top = 20.dp)
        )

        TextField(
            value = text,
            onValueChange = onTextChange,
            maxLines = 1,
            shape= RoundedCornerShape(10),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = TextFieldColor,
                textColor = White.copy(ContentAlpha.medium)
            ),
            modifier = modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
                .height(50.dp),
        )
        Text(
            text = stringResource(R.string.forgetKey_zh),
            style = MaterialTheme.typography.caption,
            color = Teal200,
            modifier = Modifier
                .alpha(ContentAlpha.high)
                .padding(bottom = 8.dp)
        )
        Text(
            text = stringResource(R.string.useKeyManager_zh),
            style = MaterialTheme.typography.caption,
            color = Teal200,
            modifier = Modifier
                .alpha(ContentAlpha.high)
                .padding(bottom = 8.dp)
        )


    }

}

