package com.yyin.dischat.ui.navigation

import android.os.Parcelable
import androidx.compose.runtime.*

@Composable
inline fun <reified T : Parcelable> rememberAppNavigatorBackstack(
    initial: T
): AppNavigator<T> {
    return remember {
        AppNavigatorBackstackImpl(initial)
    }
}

interface AppNavigator<T : Parcelable> {
    // 当前界面
    val current: T
    // 前往一个界面
    fun navigate(screen: T)
    // 返回上一个界面
    fun back(): Boolean
}

class AppNavigatorBackstackImpl<T : Parcelable>(initial: T) : AppNavigator<T> {

    // 外部传入初始界面的可组合项，存储初始界面到界面状态List
    private val _current = mutableStateListOf(initial)
    // 从状态List中获取最后一个界面
    override val current: T
        get() = _current.last()

    override fun navigate(screen: T) {
        //在界面List后面插入一个新界面
        _current.add(screen)
    }

    override fun back(): Boolean {
        if (_current.isNotEmpty()) {
            //删除当前界面
            _current.removeLast()
            return true
        }
        return false
    }
}