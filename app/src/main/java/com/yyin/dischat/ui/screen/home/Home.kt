package com.yyin.dischat.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yyin.opc.OverlappingPanels
import com.yyin.opc.OverlappingPanelsValue
import com.yyin.opc.rememberOverlappingPanelsState

@Composable
fun HomeScreen(
    onSettingsClick: () -> Unit,
    onPinsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // 设置研磨面板的状态
    val panelState = rememberOverlappingPanelsState(OverlappingPanelsValue.Closed)
    Surface(modifier = modifier) {
        OverlappingPanels(
            modifier = Modifier.fillMaxSize(),
            panelsState = panelState,
            panelStart = {
                // 左侧Channel选择面板
                GuildsChannelsScreen(
                    onGuildSelect = {
                        // TODO
                    },
                    onChannelSelect = {
                        // TODO
                    },
                    onSettingsClick = onSettingsClick,
                )
            },
            panelCenter = {/* TODO */},
            panelEnd = {/* TODO */}
        )
    }
}