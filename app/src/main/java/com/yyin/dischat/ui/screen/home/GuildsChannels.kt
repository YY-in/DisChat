package com.yyin.dischat.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import com.yyin.dischat.R
import com.yyin.dischat.domain.model.DomainCustomStatus
import com.yyin.dischat.domain.model.DomainGuild
import com.yyin.dischat.domain.model.DomainUserStatus
import com.yyin.dischat.ui.component.basic.AsyncImageLoader
import com.yyin.dischat.ui.component.basic.OCBadgeBox
import com.yyin.dischat.ui.widget.*
import com.yyin.dischat.util.ifNotNullComposable
import com.yyin.dischat.viewmodel.CurrentUserViewModel
import com.yyin.dischat.viewmodel.GuildsViewModel
import org.koin.androidx.compose.getViewModel

/**
 * 工会频道主界面
 */
@Composable
fun GuildsChannelsScreen(
    onSettingsClick: () -> Unit,
    onGuildSelect: () -> Unit,
    onChannelSelect: () -> Unit,
    modifier: Modifier = Modifier,
    guildsViewModel: GuildsViewModel = getViewModel(),
//    channelsViewModel: ChannelsViewModel = getViewModel(),
    currentUserViewModel: CurrentUserViewModel = getViewModel()
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = Modifier.weight(1f),
        ) {
            // 左侧工会列表
            GuildsList(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(72.dp),
                onGuildSelect = onGuildSelect,
                viewModel = guildsViewModel
            )
            // 右侧频道列表
//            ChannelsList()
        }
        // 底部用户信息
        CurrentUserItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp),
            viewModel = currentUserViewModel,
            onSettingsClick = onSettingsClick
        )
    }
}

/**
 * 工会列表状态集合
 */
@Composable
private fun GuildsList(
    onGuildSelect: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GuildsViewModel = getViewModel()
) {
    when (viewModel.state) {
        GuildsViewModel.State.Loading -> {
            GuildsListLoading(modifier = modifier)
        }
        GuildsViewModel.State.Loaded -> {
            GuildsListLoaded(
                modifier = modifier,
                onGuildSelect = {
                    viewModel.selectGuild(it)
                    onGuildSelect()
                },
                guilds = viewModel.guilds.values.toList(),
                selectedGuildId = viewModel.selectedGuildId
            )
        }
        GuildsViewModel.State.Error -> {

        }
    }
}

@Composable
private fun CurrentUserItem(
    modifier: Modifier = Modifier,
    onSettingsClick: () -> Unit,
    viewModel: CurrentUserViewModel = getViewModel()
) {
    var showStatusSheet by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier,
        onClick = { showStatusSheet = true },
        shape = MaterialTheme.shapes.medium
    ) {
        when (viewModel.state) {
            CurrentUserViewModel.State.Loading -> {
                CurrentUserItemLoading(
                    onSettingsClick = onSettingsClick
                )
            }
            CurrentUserViewModel.State.Loaded -> {
                CurrentUserItemLoaded(
                    onSettingsClick = onSettingsClick,
                    avatarUrl = viewModel.avatarUrl,
                    username = viewModel.username,
                    discriminator = viewModel.discriminator,
                    status = viewModel.userStatus,
                    isStreaming = viewModel.isStreaming,
                    customStatus = viewModel.userCustomStatus
                )
            }
            CurrentUserViewModel.State.Error -> {

            }
        }
    }
}


@Composable
fun CurrentUserItemLoading(
    onSettingsClick: () -> Unit
) {
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.View)
    WidgetCurrentUser(
        avatar = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shimmer(shimmer)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)),
            )
        },
        username = {
            val spaces = remember { (15..30).random() }
            Text(
                text = " ".repeat(spaces),
                modifier = Modifier
                    .shimmer(shimmer)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
            )
        },
        discriminator = {
            Text(
                text = " ".repeat(10),
                modifier = Modifier
                    .shimmer(shimmer)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f))
            )
        },
        buttons = {
            IconButton(onClick = onSettingsClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_settings),
                    contentDescription = stringResource(R.string.settings_open)
                )
            }
        },
        customStatus = null
    )
}


@Composable
fun CurrentUserItemLoaded(
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


/**
 * 工会加载中
 */
@Composable
private fun GuildsListLoading(
    modifier: Modifier = Modifier,
 ) {
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.View)
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .verticalScroll(
                state = rememberScrollState(),
                enabled = false,
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        WidgetGuildListItem(
            selected = false,
            showIndicator = false,
            onClick = {}
        ) {
            WidgetGuildContentVector {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center),
                    painter = painterResource(R.drawable.ic_discord_logo),
                    contentDescription = stringResource(R.string.guilds_home),
                )
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth(0.55f)
                .padding(bottom = 4.dp)
                .clip(MaterialTheme.shapes.medium),
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.outline,
        )

        val count = remember { (4..10).random() }
        repeat(count) {
            Box(
                modifier = Modifier
                    .shimmer(shimmer)
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
            )
        }
    }
}


@Composable
fun GuildsListLoaded(
    onGuildSelect: (Long) -> Unit,
    selectedGuildId: Long,
    guilds: List<DomainGuild>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        // home 返回标签
        item {
            WidgetGuildListItem(
                modifier = Modifier.fillParentMaxWidth(),
                selected = false,
                showIndicator = false,
                onClick = {}
            ) {
                WidgetGuildContentVector {
                    Icon(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.Center),
                        painter = painterResource(R.drawable.ic_discord_logo),
                        contentDescription = stringResource(R.string.guilds_home),
                    )
                }
            }
        }

        item {
            // 分割线
            Divider(
                modifier = Modifier
                    .fillParentMaxWidth(0.55f)
                    .padding(bottom = 4.dp)
                    .clip(MaterialTheme.shapes.medium),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.outline,
            )
        }

        items(guilds) { guild ->
            WidgetGuildListItem(
                modifier = Modifier.fillParentMaxWidth(),
                selected = selectedGuildId == guild.id,
                showIndicator = true,
                onClick = {
                    onGuildSelect(guild.id)
                }
            ) {
                // 工会图标
                if (guild.iconUrl != null) {
                    WidgetGuildContentImage(
                        url = guild.iconUrl
                    )
                } else {
                    //工会名称
                    WidgetGuildContentVector {
                        Text(guild.iconText)
                    }
                }
            }
        }
    }
}