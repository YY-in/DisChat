package com.yyin.dischat.ui.component.welcome.register

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


//@Preview
@Composable
fun SwitchBar() {
    val allScreens = RegisterMethodScreen.values().toList()
    var currentScreen by rememberSaveable {
        mutableStateOf(RegisterMethodScreen.PhoneRegister)
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        RegisterMethodSwitchBar(
            allScreens = allScreens,
            onTabSelected = { screen -> currentScreen = screen },
            currentScreen = currentScreen
        )
        currentScreen.content(onScreenChange = { screen -> currentScreen = screen })
    }

}

@Composable
fun RegisterMethodSwitchBar(
    allScreens: List<RegisterMethodScreen>,
    onTabSelected: (RegisterMethodScreen) -> Unit,
    currentScreen: RegisterMethodScreen
) {
    Surface(
        color= MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        //将组件配置为可选择，通常作为互斥组的一部分，在任何时间点只能选择该组中的一项。
        Row(
            Modifier.selectableGroup(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            allScreens.forEach { screen ->
                RegisterMethodTab(
                    text = screen.text,
                    onSelected = { onTabSelected(screen) },
                    selected = currentScreen == screen
                )
            }
        }
    }
}

@Composable
fun RegisterMethodTab(
    text: String,
    onSelected: () -> Unit,
    selected: Boolean
) {
    //色彩设定
    val color = MaterialTheme.colors.onSurface
    val backGroundColor = MaterialTheme.colors.surface
    //转移动画
    val durationMillis = if (selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration
    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }
    val textTintColor by animateColorAsState(
        targetValue = if (selected) color else color.copy(alpha = InactiveTabOpacity),
        animationSpec = animSpec
    )
    val tabTintColor by animateColorAsState(
        targetValue = if (selected) backGroundColor else color.copy(alpha = 0f),
        animationSpec = animSpec
    )
    Box(
       contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(180.dp)
            .padding(2.dp)
            .animateContentSize()
            .selectable(
                selected = selected,
                onClick = onSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                //在发生互动时为此组件绘制视觉效果。此处使用波纹效果
                indication = rememberRipple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified
                )
            )
            .background(color = tabTintColor, shape = RoundedCornerShape(10))
            //清除所有后代节点的语义并设置新语义。
            // 语义树不包含有关如何绘制可组合项的信息，而是包含关于可组合项的语义含义的信息。
            .clearAndSetSemantics { contentDescription = text }
    ) {
        Text(
            text = text,
            color = textTintColor,
            fontWeight = FontWeight.Bold,
        )
    }


}


private const val InactiveTabOpacity = 0.60f
private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100