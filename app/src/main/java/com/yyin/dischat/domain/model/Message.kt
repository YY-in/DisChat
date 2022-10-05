package com.yyin.dischat.domain.model

import com.yyin.dischat.util.SimpleAstParser
import com.yyin.dischat.util.Timestamp
import com.xinto.partialgen.Partialable
import kotlinx.datetime.Instant
import org.koin.androidx.compose.get
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

sealed class DomainMessage {
    abstract val id: Long
    abstract val channelId: Long
    abstract val timestamp: Instant
    abstract val content: String
    abstract val author: DomainUser
    //懒加载注入时间戳变量
    val formattedTimestamp by lazy { Timestamp.getFormattedTimestamp(timestamp) }
}

// 生成全参构造器子类
@Partialable
data class DomainMessageRegular(
    override val id: Long,
    override val channelId: Long,
    override val timestamp: Instant,
    override val content: String,
    override val author: DomainUser,
    val editedTimestamp: Instant?,
    val attachments: List<DomainAttachment>,
    val embeds: List<DomainEmbed>,
    val isReply: Boolean,
    val referencedMessage: DomainMessageRegular?,
    val mentionEveryone: Boolean,
//    val mentionedRoles: List<DomainRole>,
    val mentions: List<DomainUser>,
) : DomainMessage(), KoinComponent {
    private val parser: SimpleAstParser = get()

    val isEdited = editedTimestamp != null
    val contentNodes = parser.parse(content, null)
}

data class DomainMessageMemberJoin(
    override val id: Long,
    override val channelId: Long,
    override val timestamp: Instant,
    override val content: String,
    override val author: DomainUser
) : DomainMessage()