package com.yyin.dischat.ui.navigation

import android.os.Parcelable
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ContentTransform
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier

/**
 * @param [navigator] 需要提供一个导航
 * @param [transitionSpec] 过场动画
 * @param [backPressEnabled] 是否允许返回
 * @param [onBackPress] 处理返回事件
 */
@Composable
fun <T : Parcelable> AppNavigation(
    navigator: AppNavigator<T>,
    modifier: Modifier = Modifier,
    transitionSpec: AnimatedContentScope<T>.() -> ContentTransform,
    backPressEnabled: Boolean,
    onBackPress: () -> Unit,
    content: @Composable (T) -> Unit,
) {
    val saveableStateHolder = rememberSaveableStateHolder()
    BackHandler(
        enabled = backPressEnabled,
        onBack = onBackPress
    )
    AnimatedContent(
        modifier = modifier,
        targetState = navigator.current,
        transitionSpec = transitionSpec
    ) { animatedCurrentItem ->
        saveableStateHolder.SaveableStateProvider(animatedCurrentItem) {
            content(animatedCurrentItem)
        }
    }
}