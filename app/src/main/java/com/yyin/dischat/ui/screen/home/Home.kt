package com.yyin.dischat.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yyin.dischat.viewmodel.ChannelsViewModel
import com.yyin.opc.OverlappingPanels
import com.yyin.opc.OverlappingPanelsValue
import com.yyin.opc.rememberOverlappingPanelsState
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    onSettingsClick: () -> Unit,
    onPinsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // 设置研磨面板的状态
    val panelState = rememberOverlappingPanelsState(OverlappingPanelsValue.Closed)
    val channelsViewModel: ChannelsViewModel = getViewModel()


    Surface(modifier = modifier) {
        OverlappingPanels(
            modifier = Modifier.fillMaxSize(),
            panelsState = panelState,
            panelStart = {
                // 左侧Channel选择面板
                GuildsChannelsScreen(
                    //点击工会加载成员和频道
                    onGuildSelect = {
                        channelsViewModel.load()

                    },
                    //点击频道加载聊天室
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