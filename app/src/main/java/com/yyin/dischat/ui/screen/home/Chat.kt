package com.yyin.dischat.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import com.yyin.dischat.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import com.yyin.dischat.ui.widget.WidgetChatMessage
import com.yyin.dischat.viewmodel.ChatViewModel
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
fun ChatScreenPreview() {
    ChatScreen(ChatViewModel())
}

@Composable
fun ChatScreen(viewModel: ChatViewModel = getViewModel()) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = { Text(stringResource(R.string.chat_title, viewModel.channelName)) },
                navigationIcon = {
                    IconButton({/*TODO onChannelButtonClick*/ }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_menu),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton({/*TODO onPinsButtonClick*/ }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_push_pin),
                            contentDescription = null
                        )
                    }
                    IconButton({/*TODO onMembersButtonClick*/ }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_people),
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            tonalElevation = 2.dp
        ) {
            ChatScreenLoading()

        }
    }
}

/**
 * A prepare state before you select a channel.
 */
@Composable
private fun ChatScreenUnselected(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        ProvideTextStyle(MaterialTheme.typography.titleMedium) {
            Text(stringResource(R.string.chat_unselected_message))
        }
    }
}

/**
 * A loading state for the chat screen.
 * Use shimmering placeholders to indicate that the content is loading.
 */
@Composable
private fun ChatScreenLoading(
    modifier: Modifier = Modifier,
) {
    // 闪光加载动画
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.View)
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .verticalScroll(
                state = rememberScrollState(),
                enabled = false
            )
    ) {
        repeat(10) {
            WidgetChatMessage(
                modifier = Modifier.fillMaxWidth(),
                avatar = {
                    Box(
                        modifier = Modifier
                            .shimmer(shimmer)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f))
                    )
                },
                author = {
                    //TODO use WidgetMessageAuthor
                    val width = remember { (30..100).random().dp }
                    Box(
                        modifier = Modifier
                            .shimmer(shimmer)
                            .size(width = width, height = 14.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                    )
                },
                content = {
                    val rowCount = remember { (1..3).random() }
                    repeat(rowCount) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            val itemCount = (1..5).random()
                            repeat(itemCount) {
                                val spaces = remember { (10..30).random() }
                                Text(
                                    text = " ".repeat(spaces),
                                    modifier = Modifier
                                        .shimmer(shimmer)
                                        .padding(top = 8.dp)
                                        .clip(MaterialTheme.shapes.medium)
                                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f))
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}


