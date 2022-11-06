package com.yyin.dischat.ui.screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yyin.dischat.ui.navigation.AppNavigation
import com.yyin.dischat.ui.navigation.MainScreen
import com.yyin.dischat.ui.navigation.rememberAppNavigatorBackstack
import com.yyin.dischat.ui.screen.home.HomeScreen

@Composable
fun MainRootScreen() {
    val navigator = rememberAppNavigatorBackstack<MainScreen>(initial = MainScreen.Home)
    AppNavigation(
        modifier = Modifier.fillMaxSize(),
        navigator = navigator,
        transitionSpec = {
            if (initialState == MainScreen.Home) {
                slideIntoContainer(
                    towards = AnimatedContentScope.SlideDirection.Start,
                    initialOffset = { it }
                ) with fadeOut() + slideOutOfContainer(
                    towards = AnimatedContentScope.SlideDirection.Start,
                    targetOffset = { it / 3 }
                )
            } else {
                fadeIn() + slideIntoContainer(
                    towards = AnimatedContentScope.SlideDirection.End,
                    initialOffset = { it / 3 }
                ) with slideOutOfContainer(
                    towards = AnimatedContentScope.SlideDirection.End,
                    targetOffset = { it }
                )
            }
        },
        backPressEnabled = true,
        onBackPress = { navigator.back() }
    ) {
        when (it) {
            is MainScreen.Home -> {
                HomeScreen(
                    modifier = Modifier.fillMaxSize(),
                    onSettingsClick = {
                        navigator.navigate(MainScreen.Settings)
                    },
                    onPinsClick = {
                        navigator.navigate(MainScreen.Pins)
                    }
                )
            }
            is MainScreen.Pins -> {
//                ChannelPinsScreen(
//                    modifier = Modifier.fillMaxSize(),
//                    onBackClick = {
//                        navigator.back()
//                    }
//                )
            }
            is MainScreen.Settings -> {
//                Settings(
//                    modifier = Modifier.fillMaxSize(),
//                    onBackClick = {
//                        navigator.back()
//                    }
//                )
            }
        }
    }
}