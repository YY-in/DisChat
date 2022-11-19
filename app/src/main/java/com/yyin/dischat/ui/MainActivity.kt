package com.yyin.dischat.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yyin.dischat.ui.screen.MainRootScreen
import com.yyin.dischat.ui.theme.DisChatTheme

class MainActivity:ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisChatTheme(darkMode = true) {
                val systemUiController = rememberSystemUiController()
                val isLight = false
                val surface = MaterialTheme.colorScheme.surface

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = surface,
                        darkIcons = isLight,
                    )
                }
                MainRootScreen()
            }
        }
    }
}