package com.yyin.dischat.ui.screen.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyin.dischat.ui.theme.DarkColor
import com.yyin.dischat.ui.welcome.ButtonColumn
import com.yyin.dischat.ui.welcome.DisplayBoard
import com.yyin.dischat.ui.welcome.DisplayLogo
import com.yyin.dischat.ui.welcome.WelcomeText

@Preview
@Composable
fun WelcomePageScreen(){
    Scaffold(
        backgroundColor = DarkColor,
    ) {
        Column {
            Spacer(modifier = Modifier.paddingFromBaseline(100.dp, 40.dp))
            DisplayLogo()
            Spacer(modifier = Modifier.paddingFromBaseline(80.dp, 40.dp))
            DisplayBoard(modifier = Modifier)
            Spacer(modifier = Modifier.paddingFromBaseline(60.dp, 30.dp))
            WelcomeText()
            Spacer(modifier = Modifier.paddingFromBaseline(40.dp, 20.dp))
            ButtonColumn(modifier = Modifier.padding(horizontal = 20.dp))
        }
    }
}