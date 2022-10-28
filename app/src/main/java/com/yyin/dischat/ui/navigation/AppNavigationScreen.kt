package com.yyin.dischat.ui.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 *  [sealed class] 密封类拥有枚举类和普通类的特性，用来密封route对象，来实现类的匹配
 *
 */

abstract class BaseNavigationScreen(val route: String) : Parcelable {
    // is 用于检查类型 == instanceOf,用于路由的检查
    override fun equals(other: Any?): Boolean {
        return other is BaseNavigationScreen && this.route == other.route
    }

    override fun hashCode(): Int {
        return route.hashCode()
    }
}

sealed class MainScreen(route: String) : BaseNavigationScreen(route) {

    @Parcelize
    object Home : MainScreen("home")

    @Parcelize
    object Pins : MainScreen("pins")

    @Parcelize
    object Settings : MainScreen("settings")

}

sealed class UserManageScreen(route: String) : BaseNavigationScreen(route) {

    @Parcelize
    object Landing : UserManageScreen("landing")

    @Parcelize
    object Login : UserManageScreen("login")

    @Parcelize
    object Register : UserManageScreen("register")

    @Parcelize
    object ForgetPW : UserManageScreen("forgetPW")

    @Parcelize
    object ChangePW : UserManageScreen("changePW")

}