package com.yyin.dischat.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yyin.dischat.ui.screen.UserManageRootScreen
import com.yyin.dischat.ui.theme.DisChatTheme
import org.koin.android.ext.android.get

class LandingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisChatTheme {
                UserManageRootScreen()
            }
        }
    }
}
