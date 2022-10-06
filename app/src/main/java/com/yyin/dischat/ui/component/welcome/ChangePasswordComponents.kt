package com.yyin.dischat.ui.component.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.HighlightOff
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yyin.dischat.R
import com.yyin.dischat.ui.theme.Indigo700
import com.yyin.dischat.ui.theme.White


@Composable
fun ChangePasswordText() {
    Text(
        text = stringResource(R.string.update_password_tip),
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.W900,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .alpha(ContentAlpha.high)
            .padding(top = 15.dp, bottom = 8.dp)
    )
    Text(
        text = stringResource(R.string.update_password_caption),
        style = MaterialTheme.typography.caption,
        fontWeight = FontWeight.W900,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .alpha(ContentAlpha.medium)
            .padding(bottom = 15.dp)
    )
}

@Composable
fun TopChangeBar() {
    SmallTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.update_password_tip),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.alpha(ContentAlpha.medium)
            )

        },
        navigationIcon = {
            IconButton(onClick = { /* TODO:doSomething() */ }) {
                Icon(
                    Icons.Filled.HighlightOff,
                    contentDescription = "return",
                    tint = LocalContentColor.current.copy(alpha = ContentAlpha.medium),
                )
            }
        },
        )
}

@Composable
fun ChangePasswordTextField() {
    Column(modifier = Modifier.padding(20.dp)) {
        BasicTextFieldSample(hint = stringResource(id = R.string.current_password_hint))
        BasicTextFieldSample(hint = stringResource(id = R.string.new_password_hint))
        BasicTextFieldSample(hint = stringResource(id = R.string.confirm_new_password_hint))
    }
}

@Composable
fun BasicTextFieldSample(
    hint:String
) {
    //TODO : send to viewModel
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    var value by rememberSaveable { mutableStateOf("") }
    Text(
        text = hint,
        style = MaterialTheme.typography.caption,
        fontWeight = FontWeight.W500,
        modifier = Modifier
            .alpha(ContentAlpha.medium)
            .padding(bottom = 8.dp)
    )
    TextField(
        value = value,
        onValueChange = { value = it },
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
}

@Composable
fun ChangeButtons() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Indigo700,
            contentColor = White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(stringResource(R.string.done))
    }
}