package com.yyin.dischat.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import com.yyin.dischat.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
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
            TopAppBar(
                title = { Text(stringResource(R.string.chat_title,viewModel.channelName)) },
                navigationIcon = {
                    IconButton({/*TODO onChannelButtonClick*/}) {
                        Icon(
                            painter = painterResource(R.drawable.ic_menu),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton({/*TODO onPinsButtonClick*/}) {
                        Icon(
                            painter = painterResource(R.drawable.ic_push_pin),
                            contentDescription = null
                        )
                    }
                    IconButton({/*TODO onMembersButtonClick*/}) {
                        Icon(
                            painter = painterResource(R.drawable.ic_people),
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ) {paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            tonalElevation = 2.dp
        ) {


        }
    }
}

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

@Composable
private fun ChatScreenLoading(
    modifier: Modifier = Modifier,
){
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.View )
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .verticalScroll(
                state = rememberScrollState(),
                enabled = false
            )
    ) {
        repeat(10) {

        }
    }
}
