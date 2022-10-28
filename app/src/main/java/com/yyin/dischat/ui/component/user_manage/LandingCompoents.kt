package com.yyin.dischat.ui.component.user_manage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.yyin.dischat.R
import com.yyin.dischat.ui.theme.BlueGray400
import com.yyin.dischat.ui.theme.Indigo700
import com.yyin.dischat.ui.theme.White

@Composable
fun DisplayBoard(modifier: Modifier) {
    Image(
        painter = painterResource(R.drawable.displayboard),
        contentDescription = "展板",
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun ButtonColumn(
    modifier: Modifier =Modifier,
    onClickRegister: () -> Unit,
    oneClickLogin: ()-> Unit
    ) {
    Column {
        Button(
            onClick = onClickRegister ,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Indigo700,
                contentColor = White
            ),
            modifier = modifier.fillMaxWidth()
        ) {

            Text(stringResource(R.string.register))
        }
        Button(
            onClick =  oneClickLogin ,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = BlueGray400,
                contentColor = White
            ),
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(stringResource(R.string.login))

        }
    }
}

@Composable
fun WelcomeText() {
    Column() {
        Text(
            text = stringResource(R.string.welcomeTitle),
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()

        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = stringResource(R.string.welcomeOverline),
                style = MaterialTheme.typography.overline,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun DisplayLogo(modifier: Modifier=Modifier) {
    Row() {
        Image(
            painterResource(
                R.drawable.dischat_logo
            ),
            contentDescription = "Logon",
            modifier = modifier
                .fillMaxWidth()
                .scale(1.5f)
        )

    }
}

