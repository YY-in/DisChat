package com.yyin.dischat.ui.component.user_manage.register

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun RegisterTextField(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    onClickCodeButton: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = placeholder,
            style = MaterialTheme.typography.overline,
            fontWeight = W500,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = 10.dp, top = 20.dp)
        )

        BaseRegisterTextField(
            value = value,
            onValueChange = onValueChange,
            isError = isError,
            placeholder =  placeholder,
            onClickCodeButton = onClickCodeButton,
        )
    }

}

@Composable
fun BaseRegisterTextField(
    isError: Boolean = false,
    placeholder: String,
    value: String ,
    onClickCodeButton: () -> Unit,
    onValueChange: (String) -> Unit
) {
    var countryCode by remember {
        mutableStateOf("中国 +86")
    }

    var expanded by remember {
        mutableStateOf(true)
    }

    if (placeholder == "电话号码"){
        expanded = true
    }else if(placeholder == "邮箱地址"){
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
            isError = isError,
            placeholder= {Text(text=placeholder)},
            value = value,
            onValueChange = onValueChange,
            maxLines = 1,
            shape = RoundedCornerShape(10),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                textColor =  LocalContentColor.current.copy(alpha = ContentAlpha.high),
                errorIndicatorColor = MaterialTheme.colors.error,
            ),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        )
    }
}





