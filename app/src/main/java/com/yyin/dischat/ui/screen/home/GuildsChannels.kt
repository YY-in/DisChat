package com.yyin.dischat.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyin.dischat.viewmodel.GuildsViewModel
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
fun GuildsChannelsPreview(){

}

@Composable
fun GuildsChannelsScreen(
    onSettingsClick: () -> Unit,
    onGuildSelect: () -> Unit,
    onChannelSelect: () -> Unit,
    modifier: Modifier = Modifier,
    guildsViewModel: GuildsViewModel = getViewModel(),
//    channelsViewModel: ChannelsViewModel = getViewModel(),
//    currentUserViewModel: CurrentUserViewModel = getViewModel()
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = Modifier.weight(1f),
        ){
            // 左侧Guilds列表
            GuildsList(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(72.dp),
                onGuildSelect = onGuildSelect,
                viewModel = guildsViewModel
            )
            // 右侧Channels列表
//            ChannelsList()
        }
        // 底部用户信息
//        CurrentUserItem()
    }
}

@Composable
private fun GuildsList(
    onGuildSelect: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GuildsViewModel = getViewModel()
) {
//    when(viewModel.state){
//        is GuildsViewModel.State.Loading -> {
//
//        }
//        is GuildsViewModel.State.Loaded ->{
//
//        }
//        is GuildsViewModel.State.Error -> {
//
//        }
//    }

}

@Composable
private fun CurrentUserItem(
    modifier: Modifier = Modifier,
    onSettingsClick: () -> Unit,
    viewModel: GuildsViewModel = getViewModel()
) {
    var showStatusSheet by remember { mutableStateOf(false) }


}
