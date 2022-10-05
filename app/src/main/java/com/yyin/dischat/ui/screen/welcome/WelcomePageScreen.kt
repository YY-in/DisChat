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
import com.yyin.dischat.ui.component.ButtonColumn
import com.yyin.dischat.ui.component.DisplayBoard
import com.yyin.dischat.ui.component.DisplayLogo
import com.yyin.dischat.ui.component.WelcomeText

@Preview
@Composable
fun WelcomePageScreen(){
    Scaffold(
    ) {
        Column {
            Spacer(modifier = Modifier.paddingFromBaseline(100.dp, 40.dp))
            DisplayLogo()
            Spacer(modifier = Modifier.paddingFromBaseline(80.dp, 80.dp))
            DisplayBoard(modifier = Modifier.padding(1.dp))
            Spacer(modifier = Modifier.paddingFromBaseline(60.dp, 30.dp))
            WelcomeText()
            Spacer(modifier = Modifier.paddingFromBaseline(40.dp, 20.dp))
            ButtonColumn(modifier = Modifier.padding(horizontal = 20.dp))
        }
    }
}