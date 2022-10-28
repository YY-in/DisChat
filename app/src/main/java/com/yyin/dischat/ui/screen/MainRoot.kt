package com.yyin.dischat.ui.screen

import androidx.compose.runtime.Composable
import com.yyin.dischat.ui.navigation.MainScreen
import com.yyin.dischat.ui.navigation.rememberAppNavigatorBackstack

@Composable
fun MainRootScreen(){
    val navigator = rememberAppNavigatorBackstack<MainScreen>(initial = MainScreen.Home)
}