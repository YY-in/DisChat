package com.yyin.dischat.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yyin.dischat.R
import com.yyin.dischat.domain.model.DomainCustomStatus
import com.yyin.dischat.domain.model.DomainUserStatus
import com.yyin.dischat.ui.component.basic.AsyncImageLoader
import com.yyin.dischat.ui.component.basic.OCBadgeBox
import com.yyin.dischat.ui.widget.WidgetCurrentUser
import com.yyin.dischat.ui.widget.WidgetStatusIcon
import com.yyin.dischat.util.ifNotNullComposable
import com.yyin.dischat.viewmodel.GuildsViewModel
import org.koin.androidx.compose.getViewModel

//@Preview
//@Composable
//fun GuildsChannelsPreview() {
//
//}

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
        ) {
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

    Surface(
        modifier = modifier,
        onClick = { showStatusSheet = true },
        shape = MaterialTheme.shapes.medium
    ) {
        when (viewModel.state) {
            GuildsViewModel.State.Error -> TODO()
            GuildsViewModel.State.Loaded -> TODO()
            GuildsViewModel.State.Loading -> TODO()
        }
    }

}

@Preview
@Composable
private fun CurrentUserItemLoadedPreview() {
    Column{
        CurrentUserItemLoaded(
            onSettingsClick = {},
            avatarUrl="https://qiniu.yyin.top/yuji.png",
            username = "YuJi",
            discriminator="#1253",
            status=DomainUserStatus.Idle,
            isStreaming= false,
            customStatus = null
        )
        CurrentUserItemLoaded(
            onSettingsClick = {},
            avatarUrl="https://qiniu.yyin.top/link.png",
            username = "Link",
            discriminator="#7865",
            status=DomainUserStatus.Online,
            isStreaming= false,
            customStatus = DomainCustomStatus("I'm a prince ",null,null,null)
        )
        CurrentUserItemLoaded(
            onSettingsClick = {},
            avatarUrl="https://qiniu.yyin.top/lucy.png",
            username = "Lucy",
            discriminator="#6532",
            status=DomainUserStatus.Dnd,
            isStreaming= false,
            customStatus =DomainCustomStatus("fly me to the moon",null,null,null)
        )
        CurrentUserItemLoaded(
            onSettingsClick = {},
            avatarUrl="https://qiniu.yyin.top/zelda.png",
            username = "Zelda",
            discriminator="#5874",
            status=DomainUserStatus.Invisible,
            isStreaming= false,
            customStatus = DomainCustomStatus("I'm a princess",null,null,null)
        )
    }

}

@Composable
private fun CurrentUserItemLoaded(
    onSettingsClick: () -> Unit,
    avatarUrl: String,
    username: String,
    discriminator: String,
    status: DomainUserStatus?,
    isStreaming: Boolean,
    customStatus: DomainCustomStatus?,
) {
    WidgetCurrentUser(
        avatar = {
            OCBadgeBox(
                badge = status.ifNotNullComposable { userStatus ->
                    WidgetStatusIcon(
                        modifier = Modifier.size(10.dp),
                        isStreaming = isStreaming,
                        userStatus = userStatus
                    )
                }
            ) {
                AsyncImageLoader(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    url = avatarUrl
                )
            }
        },
        username = { Text(username) },
        discriminator = { Text(discriminator) },
        customStatus = customStatus?.text?.ifNotNullComposable { Text(it) },
        buttons = {
            IconButton(onClick = onSettingsClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_settings),
                    contentDescription = stringResource(R.string.settings_open)
                )
            }
        },
    )
}
