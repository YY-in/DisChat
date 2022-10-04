package com.yyin.dischat.ui.screen.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyin.dischat.R
import com.yyin.dischat.ui.component.register.SwitchBar
import com.yyin.dischat.ui.component.register.TopRegisterBar
import com.yyin.dischat.ui.theme.DarkColor
import com.yyin.dischat.ui.theme.Indigo700
import com.yyin.dischat.ui.theme.White

@Preview
@Composable
fun RegisterCard() {
    Scaffold(
        topBar = { TopRegisterBar() },
        backgroundColor = DarkColor
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.registerTip),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.W900,
                color = White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .padding(vertical = 15.dp)
            )
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                SwitchBar()
            }
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
                Text(stringResource(R.string.next))
            }
        }

    }
}