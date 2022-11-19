package com.yyin.dischat.ui.screen

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import com.yyin.dischat.ui.navigation.AppNavigation
import com.yyin.dischat.ui.navigation.UserManageScreen
import com.yyin.dischat.ui.navigation.rememberAppNavigatorBackstack
import com.yyin.dischat.ui.screen.user_manage.*

@Composable
fun UserManageRootScreen() {
    // 设置当前导航--> 一个界面名称的List
    val navigator = rememberAppNavigatorBackstack<UserManageScreen>(initial = UserManageScreen.Landing)
        AppNavigation(
            navigator = navigator,
            transitionSpec = {
                fadeIn() with fadeOut()
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
                        onAuthorized = {
                            navigator.navigate(UserManageScreen.ChangePW)
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
                        },
                        onConfirm = {
                            navigator.navigate(UserManageScreen.RegisterDetail)
                        }
                    )
                }
                is UserManageScreen.RegisterDetail->{
                    RegisterDetailScreen(
                        onClickReturnLanding = {
                            navigator.navigate(UserManageScreen.Register)
                        },
                    )
                }
                is UserManageScreen.ChangePW->{
                    ChangePWScreen()
                }
            }
        }
}