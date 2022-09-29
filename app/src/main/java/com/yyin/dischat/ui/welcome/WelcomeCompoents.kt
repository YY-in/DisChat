package com.yyin.dischat.ui.welcome

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            .scale(1.2f)
    )
}

@Composable
fun ButtonColumn(modifier: Modifier =Modifier) {
    Column {
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Indigo700,
                contentColor = White
            ),
            modifier = modifier.fillMaxWidth()
        ) {

            Text(stringResource(R.string.register))
        }
        Button(
            onClick = { /*TODO*/ },
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
            color = White,
            modifier = Modifier.fillMaxWidth()

        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = stringResource(R.string.welcomeOverline),
                color= White,
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
                R.drawable.dischat_slogon
            ),
            contentDescription = "Logon",
            modifier = modifier
                .fillMaxWidth()
                .scale(1.5f)
        )

    }
}

@Preview
@Composable
fun PreviewWelcomeCard() {
    Column {
        Spacer(modifier = Modifier.paddingFromBaseline(58.dp, 40.dp))
        DisplayLogo()
        Spacer(modifier = Modifier.paddingFromBaseline(58.dp, 40.dp))
        DisplayBoard(modifier = Modifier)
        Spacer(modifier = Modifier.paddingFromBaseline(40.dp, 30.dp))
        WelcomeText()
        Spacer(modifier = Modifier.paddingFromBaseline(20.dp, 20.dp))
        ButtonColumn(modifier = Modifier.padding(horizontal = 20.dp))
    }


}