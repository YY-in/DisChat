package com.yyin.dischat.ui.screen

import android.os.UserManager
import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import com.yyin.dischat.ui.navigation.AppNavigation
import com.yyin.dischat.ui.navigation.UserManageScreen
import com.yyin.dischat.ui.navigation.rememberAppNavigatorBackstack
import com.yyin.dischat.ui.screen.user_manage.*

@Composable
fun UserManageRootScreen() {
    // 设置当前导航--> 一个界面名称的List
    val navigator = rememberAppNavigatorBackstack<UserManageScreen>(initial = UserManageScreen.Landing)
    CompositionLocalProvider(LocalAbsoluteTonalElevation provides 1.dp) {
        AppNavigation(
            navigator = navigator,
            transitionSpec = {
                when {
                    UserManageScreen.Landing isTransitioningTo  UserManageScreen.Login -> {
                        slideIntoContainer(
                            towards = AnimatedContentScope.SlideDirection.Start,
                            initialOffset = { it }
                        ) with fadeOut() + slideOutOfContainer(
                            towards = AnimatedContentScope.SlideDirection.Start,
                            targetOffset = { it / 3 }
                        )
                    }
                    UserManageScreen.Login isTransitioningTo UserManageScreen.Landing -> {
                        fadeIn() + slideIntoContainer(
                            towards = AnimatedContentScope.SlideDirection.End,
                            initialOffset = { it / 3 }
                        ) with slideOutOfContainer(
                            towards = AnimatedContentScope.SlideDirection.End,
                            targetOffset = { it }
                        )
                    }
                    else -> fadeIn() with fadeOut()
                }
            },
            backPressEnabled = true,
            onBackPress = { navigator.back() }
        ){
            when(it){
                is UserManageScreen.Landing -> {
                    LandingScreen(
                        onClickRegister = {
                            navigator.navigate(UserManageScreen.Register)
                        },
                        oneClickLogin = {
                            navigator.navigate(UserManageScreen.Login)
                        },
                        onAuthorized = {
                            navigator.navigate(UserManageScreen.ChangePW)
                        }
                    )
                }
                is UserManageScreen.Login ->{
                    LoginScreen(
                        onClickReturnLanding = {
                            navigator.navigate(UserManageScreen.Landing)
                        },
                        onClickForgetPW = {
                            navigator.navigate(UserManageScreen.ForgetPW)
                        },
                        onClickLogin = {
//                            navigator.navigate(UserManageScreen.Home)
                        }
                    )

                }
                is UserManageScreen.ForgetPW->{
                    ForgetPWScreen(
                        onClickReturnLogin = {
                            navigator.navigate(UserManageScreen.Login)
                        },
                        onClickConfirm = {
                            navigator.navigate(UserManageScreen.ChangePW)
                        }
                    )
                }
                is UserManageScreen.Register->{
                    RegisterScreen(
                        onClickReturnLanding = {
                            navigator.navigate(UserManageScreen.Landing)
                        }
                    )
                }
                is UserManageScreen.ChangePW->{
                    ChangePWScreen()
                }
            }
        }
    }
}