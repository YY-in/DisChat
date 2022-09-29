package com.yyin.dischat.ui.register

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.yyin.dischat.R
import com.yyin.dischat.ui.theme.*


@Composable
fun TopRegisterBar() {
    TopAppBar(
        backgroundColor = BlueGray900,
        title = {
            Text(
                text = stringResource(R.string.register_zh),
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
fun RegisterTextField(
    text: String,
    onTextChange: (String) -> Unit,
    onClickCodeButton: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.overline,
            fontWeight = W500,
            color = White,
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = 10.dp, top = 20.dp)
        )

        PhoneRegisterTextField(
            text = text,
            onTextChange = onTextChange,
            onClickCodeButton = onClickCodeButton
        )


        Text(
            text = stringResource(R.string.privacy_policy_zh),
            style = MaterialTheme.typography.caption,
            color = Teal200,
            modifier = Modifier
                .alpha(ContentAlpha.high)
                .padding(top = 10.dp)
        )
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PhoneRegisterTextField(
    text: String,
    onTextChange: (String) -> Unit,
    onClickCodeButton: () -> Unit
) {
    var countryCode by remember {
        mutableStateOf("中国 +86")
    }

    var expanded by remember {
        mutableStateOf(true)
    }
    if (text == "手机号码"){
        expanded = true
    }else if(text == "邮箱地址"){
        expanded = false
    }

    Row {
        AnimatedVisibility(
            visible = expanded,
            enter = expandHorizontally(
                expandFrom = Alignment.Start
            ),
            exit = shrinkHorizontally ()
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = TextFieldColor,
                    contentColor = White.copy(ContentAlpha.high)
                ),
                onClick = onClickCodeButton,
                modifier = Modifier
                    .height(50.dp)
                    .padding(end = 2.dp)
            ) {
                Text(text = countryCode)
            }
        }
        TextField(
            value = text,
            onValueChange = onTextChange,
            maxLines = 1,
            shape = RoundedCornerShape(10),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = TextFieldColor,
                textColor = White.copy(ContentAlpha.medium)
            ),
            modifier = Modifier
                .height(50.dp).fillMaxWidth()

        )


    }

}





