package com.yyin.dischat.ui.component.user_manage.register

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.unit.dp
import com.yyin.dischat.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun RegisterTextField(
    text: String,
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
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = 10.dp, top = 20.dp)
        )

        PhoneRegisterTextField(
            text = text,
            onClickCodeButton = onClickCodeButton
        )


        Text(
            text = stringResource(R.string.privacy_policy),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .alpha(ContentAlpha.high)
                .padding(top = 10.dp)
        )
    }

}

@Composable
fun PhoneRegisterTextField(
    text: String,
    onClickCodeButton: () -> Unit
) {
    var countryCode by remember {
        mutableStateOf("中国 +86")
    }

    var expanded by remember {
        mutableStateOf(true)
    }

    if (text == stringResource(R.string.phone)){
        expanded = true
    }else if(text == stringResource(R.string.email)){
        expanded = false
    }

    var input by rememberSaveable { mutableStateOf("") }

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
                    backgroundColor = MaterialTheme.colors.surface,
                    contentColor = LocalContentColor.current.copy(alpha = ContentAlpha.high),
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
            placeholder= {Text(text=text)},
            value = input,
            onValueChange = {input = it},
            maxLines = 1,
            shape = RoundedCornerShape(10),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                textColor =  LocalContentColor.current.copy(alpha = ContentAlpha.high)
            ),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()

        )


    }

}





