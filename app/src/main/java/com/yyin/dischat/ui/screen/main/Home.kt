package com.yyin.dischat.ui.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.yyin.dischat.ui.util.animateCornerBasedShapeAsState
import com.yyin.dischat.viewmodel.*
import com.yyin.opc.OverlappingPanels
import com.yyin.opc.OverlappingPanelsValue
import com.yyin.opc.rememberOverlappingPanelsState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    onSettingsClick: () -> Unit,
    onPinsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // 设置研磨面板的状态
    val panelState = rememberOverlappingPanelsState(OverlappingPanelsValue.Closed)
    // 定义协程域
    val coroutineScope = rememberCoroutineScope()

    val currentUserViewModel: CurrentUserViewModel = getViewModel()
    val chatViewModel: ChatViewModel = getViewModel()
    val guildsViewModel: GuildsViewModel = getViewModel()
    val channelsViewModel: ChannelsViewModel = getViewModel()
    val membersViewModel: MembersViewModel = getViewModel()
    val channelPinsViewModel: ChannelPinsViewModel = getViewModel()

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
//                        membersViewModel.load()
                    },
                    guildsViewModel = guildsViewModel,
                    currentUserViewModel = currentUserViewModel,
                    channelsViewModel = channelsViewModel,
                    onChannelSelect = chatViewModel::load,
                    onSettingsClick = onSettingsClick,
                )
            },
            // 中央聊天室
            panelCenter = {
                val shape by animateCornerBasedShapeAsState(
                    if (panelState.offsetNotZero)
                        MaterialTheme.shapes.large
                    else
                        RoundedCornerShape(0.dp)
                )
                ChatScreen(
                    onChannelsButtonClick = {
                        coroutineScope.launch {
                            panelState.openStartPanel()
                        }
                    },
                    onMembersButtonClick = {
                        coroutineScope.launch {
                            panelState.openEndPanel()
                        }
                    },
                    onPinsButtonClick = {
                        channelPinsViewModel.load()
                        onPinsClick()
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape),
                    viewModel = chatViewModel
                )
            },
            //当前Channel成员列表
            panelEnd = {
                ChannelMembersScreen(
                    // 载入成员
                 viewModel = membersViewModel
                )
            }
        )
    }
}