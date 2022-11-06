package com.yyin.dischat.ui.screen.user_manage

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yyin.dischat.R
import com.yyin.dischat.ui.component.user_manage.login.MyTopBar
import com.yyin.dischat.ui.component.user_manage.register.SwitchBar
import com.yyin.dischat.ui.theme.Indigo700
import com.yyin.dischat.ui.theme.White


@Composable
fun RegisterScreen(
    onClickReturnLanding : () -> Unit,
    onClickNext:() ->Unit
) {
    Scaffold(
        topBar = {
                 MyTopBar(
                     text = stringResource(R.string.register),
                     onClickReturn = onClickReturnLanding
                 )
        }
    ) { paddingValues ->
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
                Text(
                    text = stringResource(R.string.registerTip),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.W900,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .alpha(ContentAlpha.high)
                        .padding(vertical = 15.dp)
                )
                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                ) {
                    SwitchBar()
                }
                Button(
                    onClick = onClickNext,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Indigo700,
                        contentColor = White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(stringResource(R.string.next))
                }
            }
        }


    }
}