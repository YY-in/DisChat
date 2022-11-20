package com.yyin.dischat.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import com.yyin.dischat.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import com.xinto.simpleast.render
import com.yyin.dischat.domain.model.*
import com.yyin.dischat.ui.component.basic.BottomSheetDialog
import com.yyin.dischat.ui.widget.*
import com.yyin.dischat.util.ifComposable
import com.yyin.dischat.util.ifNotEmptyComposable
import com.yyin.dischat.util.ifNotNullComposable
import com.yyin.dischat.viewmodel.ChatViewModel
import org.koin.androidx.compose.getViewModel



@Composable
fun ChatScreen(
    onChannelsButtonClick: () -> Unit,
    onMembersButtonClick: () -> Unit,
    onPinsButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = getViewModel()
) {
    //分类信息
    val sortedMessages by remember(viewModel.messages) {
        derivedStateOf {
            viewModel.getSortedMessages()
        }
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = { Text(stringResource(R.string.chat_title, viewModel.channelName)) },
                navigationIcon = {
                    IconButton({onChannelsButtonClick }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_menu),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton({onPinsButtonClick }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_push_pin),
                            contentDescription = null
                        )
                    }
                    IconButton({onMembersButtonClick }) {
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
            /**
             * ChatScreenLoaded(
                messages = listOf(
                    DomainMessageMemberJoin(
                        id = 1,
                        channelId = 1,
                        timestamp = Clock.System.now(),
                        content = "Test Mf",
                        author = DomainUserPublic(
                            id = 12,
                            username = "YYin",
                            discriminator = "A kotlin developer",
                            bot = false,
                            bio = "hi yo",
                            flags = 1,
                            pronouns = "he/him",
                            avatarUrl = "https://qiniu.yyin.top/mybatisplus.png"
                        ),
                    )
                ) ,
                currentUserId = 12,
                channelName= "Test",
                userMessage = "Helle DisChat From YYin",
                sendEnabled = true,
                onUserMessageUpdate= {},
                onUserMessageSend={},
            )*/
            //安装状态切换界面
            when (viewModel.state) {
                is ChatViewModel.State.Unselected -> {
                    ChatScreenUnselected(
                        modifier = Modifier.fillMaxSize()
                    )
                }
                is ChatViewModel.State.Loading -> {
                    ChatScreenLoading(
                        modifier = Modifier.fillMaxSize()
                    )
                }
                is ChatViewModel.State.Loaded -> {
                    ChatScreenLoaded(
                        messages = sortedMessages,
                        currentUserId = viewModel.currentUserId,
                        channelName = viewModel.channelName,
                        userMessage = viewModel.userMessage,
                        sendEnabled = viewModel.sendEnabled,
                        onUserMessageUpdate = viewModel::updateMessage,
                        onUserMessageSend = viewModel::sendMessage,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
                is ChatViewModel.State.Error -> {
                    ChatScreenError(
                        modifier = Modifier.fillMaxSize()
                    )
                }

            }
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
                                        .background(
                                            MaterialTheme.colorScheme.onSurface.copy(
                                                alpha = 0.3f
                                            )
                                        )
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}

/**
 * A error state for the chat screen.
 */
@Composable
private fun ChatScreenError(
    modifier: Modifier = Modifier.fillMaxSize(),
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colorScheme.error,
            LocalTextStyle provides MaterialTheme.typography.titleMedium
        ) {
            Icon(
                modifier = Modifier.size(56.dp),
                painter = painterResource(R.drawable.ic_error),
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.chat_loading_error),
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * A Loaded state for the chat screen.
 * @param messages 需要加载的message
 * @param currentUserId 当前用户的ID
 */
@Composable
private fun ChatScreenLoaded(
    messages: List<DomainMessage>,
    currentUserId: Long?,
    channelName: String,
    userMessage: String,
    sendEnabled: Boolean,
    onUserMessageUpdate: (String) -> Unit,
    onUserMessageSend: () -> Unit,
    modifier: Modifier = Modifier,
) {

    // TODO: scroll to target message if jumping
    val listState = rememberLazyListState()

    // TODO: toggleable auto scroll by scrolling up
    LaunchedEffect(messages.size) {
        if (listState.firstVisibleItemIndex <= 1) {
            listState.animateScrollToItem(0)
        }
    }


    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f),
            reverseLayout = true,
        ) {
            //创建数据列表，绑定索引
            itemsIndexed(messages, key = { _, m -> m.id }) { i, message ->
                var showBottomDialog by rememberSaveable { mutableStateOf(false) }
                //存储是否被人@的状态
                val mentioned by remember {
                    derivedStateOf {
                        // 没有被人@
                        if (message !is DomainMessageRegular) false
                        else {
                            // 遍历当前的message中所有@的ID 并找到是否有人@当前用户 或者 是否是@所有人
                            message.mentions.any { it.id == currentUserId }
                                    || message.mentionEveryone
                        }
                    }
                }

                when (message) {
                    is DomainMessageRegular -> {
                        // 通过index获取当前message的上一个message
                        val prevMessage = messages.getOrNull(i + 1)
                        // 判断当前的消息是否可以和上一条消息合并，并存储该状态变量用于之后UI变化
                        val canMerge = prevMessage != null
                                && prevMessage is DomainMessageRegular
                                && message.author.id == prevMessage.author.id
                                && (message.timestamp - prevMessage.timestamp).inWholeMinutes < 1
                                && message.attachments.isEmpty()
                                && prevMessage.attachments.isEmpty()
                                && message.embeds.isEmpty()
                                && prevMessage.embeds.isEmpty()
                                && !message.isReply
                                && !prevMessage.isReply
                        WidgetChatMessage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MaterialTheme.shapes.medium)
                                .combinedClickable(
                                    onClick = {/*TODO onClick*/ },
                                    onLongClick = { showBottomDialog = true }
                                ),
                            mentioned = mentioned,
                            // 消息是回复状态的话，就显示回复的内容
                            reply = message.isReply.ifComposable {
                                // 获取需要 回复的消息
                                val referencedMessage = message.referencedMessage
                                // 如果该消息存在的话，就显示回复的内容
                                if (referencedMessage != null) {
                                    WidgetMessageReply(
                                        avatar = {
                                            WidgetMessageAvatar(url = referencedMessage.author.avatarUrl)
                                        },
                                        author = {
                                            WidgetMessageReplyAuthor(author = referencedMessage.author.username)
                                        },
                                        content = {
                                            WidgetMessageReplyContent(
                                                text = render(
                                                    nodes = referencedMessage.contentNodes,
                                                    renderContext = null
                                                ).toAnnotatedString()
                                            )
                                        }
                                    )
                                } else {
                                    // 如果该消息不存在的话，就显示一个提示
                                    ProvideTextStyle(MaterialTheme.typography.bodySmall) {
                                        Text(stringResource(R.string.message_reply_unknown))
                                    }
                                }
                            },
                            avatar = if (canMerge) null else { ->
                                WidgetMessageAvatar(url = message.author.avatarUrl)
                            },
                            author = if (canMerge) null else { ->
                                WidgetMessageAuthor(
                                    author = message.author.username,
                                    timestamp = message.formattedTimestamp,
                                    edited = message.isEdited,
                                    onAuthorClick = {
                                        onUserMessageUpdate("$userMessage${message.author.formattedMention} ")
                                    },
                                )
                            },

                            content = message.contentNodes.ifNotEmptyComposable { nodes ->
                                WidgetMessageContent(
                                    text = render(
                                        nodes = nodes,
                                        renderContext = null
                                    ).toAnnotatedString()
                                )
                            },
                            embeds = message.embeds.ifNotEmptyComposable { embeds ->
                                for (embed in embeds) {
                                    WidgetEmbed(
                                        title = embed.title,
                                        description = embed.description,
                                        color = embed.color,
                                        author = embed.author.ifNotNullComposable {
                                            WidgetEmbedAuthor(name = it.name)
                                        },
                                        fields = embed.fields.ifNotNullComposable {
                                            for (field in it) {
                                                WidgetEmbedField(
                                                    name = field.name,
                                                    value = field.value
                                                )
                                            }
                                        }
                                    )
                                }
                            },
                            attachments = message.attachments.ifNotEmptyComposable { attachments ->
                                for (attachment in attachments) {
                                    when (attachment) {
                                        is DomainAttachment.Picture -> {
                                            WidgetAttachmentPicture(
                                                modifier = Modifier
                                                    .heightIn(max = 400.dp),
                                                url = attachment.proxyUrl,
                                                width = attachment.width,
                                                height = attachment.height
                                            )
                                        }
                                        else -> {}
                                    }
                                }
                            }
                        )
                    }
                    else -> {/* TODO */
                    }
                }
                if (showBottomDialog) {
                    MessageActionMenu(
                        onDismissRequest = { showBottomDialog = false }
                    )
                }
            }
        }
        WidgetChatInput(
            modifier = Modifier.padding(
                start = 8.dp,
                end = 8.dp,
                bottom = 4.dp
            ),
            value = userMessage,
            onValueChange = onUserMessageUpdate,
            sendEnabled = sendEnabled,
            onSendClick = onUserMessageSend,
            hint = { Text(stringResource(R.string.chat_input_hint, channelName)) }
        )
    }
}


@Composable
private fun MessageActionMenu(
    onDismissRequest: () -> Unit
) {
    BottomSheetDialog(
        onDismissRequest = onDismissRequest
    ) {
        Surface {
            Column {
                Text("Hello World")
            }
        }
    }
}


