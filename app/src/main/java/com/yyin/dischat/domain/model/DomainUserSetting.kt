package com.yyin.dischat.domain.model

import com.xinto.enumgetter.GetterGen
import com.xinto.partialgen.Partialable
import kotlinx.datetime.Instant

@Partialable
data class DomainUserSettings(
    val locale: String,
    val showCurrentGame: Boolean,
//    val restrictedGuilds: List<Long>
//    val defaultGuildsRestricted: Boolean,
    val inlineAttachmentMedia: Boolean,
    val inlineEmbedMedia: Boolean,
    val gifAutoPlay: Boolean,
    val renderEmbeds: Boolean,
    val renderReactions: Boolean,
    val animateEmoji: Boolean,
    val enableTTSCommand: Boolean,
    val messageDisplayCompact: Boolean,
    val convertEmoticons: Boolean,
//    val explicitContentFilter: Int,
    val disableGamesTab: Boolean,
    val theme: DomainThemeSetting,
    val developerMode: Boolean,
    val guildPositions: List<Long>,
    val detectPlatformAccounts: Boolean,
    val status: DomainUserStatus,
    val afkTimeout: Int,
    val timezoneOffset: Int,
    val streamNotificationsEnabled: Boolean,
    val allowAccessibilityDetection: Boolean,
    val contactSyncEnabled: Boolean,
    val nativePhoneIntegrationEnabled: Boolean,
    val animateStickers: Int, // int??
    val friendDiscoveryFlags: Int, // int??
    val viewNsfwGuilds: Boolean,
    val passwordless: Boolean,
    val friendSourceFlags: DomainFriendSources,
    val guildFolders: List<DomainGuildFolder>,
    val customStatus: DomainCustomStatus?,
)

data class DomainFriendSources(
    val all: Boolean,
    val mutualFriends: Boolean,
    val mutualGuilds: Boolean,
)

data class DomainGuildFolder(
    /** null means it is outside a folder */
    val id: Long?,
    val guildIds: List<Long>,
    val name: String?,
)

// 用户自定义状态
data class DomainCustomStatus(
    val text: String?,
    val expiresAt: Instant?,
    val emojiId: Long?,
    val emojiName: String?
)

@GetterGen
enum class DomainThemeSetting(val value: String) {
    Dark("dark"),
    Light("light");

    companion object
}


@GetterGen
enum class DomainUserStatus(val value: String) {
    Online("online"),

    Idle("idle"),

    Dnd("dnd"),    // do no disturb

    Invisible("invisible");

    // 修饰为伴生对象,伴生对象在类中只能存在一个，
    // 类似于java中的静态方法 Java 中使用类访问静态成员，静态方法。
    companion object
}
