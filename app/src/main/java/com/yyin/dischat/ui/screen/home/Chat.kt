package com.yyin.dischat.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import com.yyin.dischat.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyin.dischat.viewmodel.ChatViewModel
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
fun ChatScreen(/*viewModel: ChatViewModel = getViewModel()*/) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.chat_title,/*viewModel.channelName*/)) },
                navigationIcon = {
                    IconButton({}) {
                        Icon(
                            painter = painterResource(R.drawable.ic_menu),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton({/*onPinsButtonClick*/}) {
                        Icon(
                            painter = painterResource(R.drawable.ic_push_pin),
                            contentDescription = null
                        )
                    }
                    IconButton({/*onMembersButtonClick*/}) {
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