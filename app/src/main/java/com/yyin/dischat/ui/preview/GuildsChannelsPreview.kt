package com.yyin.dischat.ui.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyin.dischat.domain.model.*
import com.yyin.dischat.ui.screen.home.*
import com.yyin.dischat.viewmodel.ChannelsViewModel


@Preview
@Composable
private fun CurrentUserItemLoadedPreview() {
    Column {
        CurrentUserItemLoaded(
            onSettingsClick = {},
            avatarUrl = "https://qiniu.yyin.top/yuji.png",
            username = "YuJi",
            discriminator = "#1253",
            status = DomainUserStatus.Idle,
            isStreaming = false,
            customStatus = null
        )
        CurrentUserItemLoaded(
            onSettingsClick = {},
            avatarUrl = "https://qiniu.yyin.top/link.png",
            username = "Link",
            discriminator = "#7865",
            status = DomainUserStatus.Online,
            isStreaming = false,
            customStatus = DomainCustomStatus("I'm a prince ", null, null, null)
        )
        CurrentUserItemLoaded(
            onSettingsClick = {},
            avatarUrl = "https://qiniu.yyin.top/lucy.png",
            username = "Lucy",
            discriminator = "#6532",
            status = DomainUserStatus.Dnd,
            isStreaming = false,
            customStatus = DomainCustomStatus("fly me to the moon", null, null, null)
        )
        CurrentUserItemLoaded(
            onSettingsClick = {},
            avatarUrl = "https://qiniu.yyin.top/zelda.png",
            username = "Zelda",
            discriminator = "#5874",
            status = DomainUserStatus.Invisible,
            isStreaming = false,
            customStatus = DomainCustomStatus("I'm a princess", null, null, null)
        )
    }

}

@Preview
@Composable
private fun CurrentUserItemLoadingPreview() {
    CurrentUserItemLoading({})
}

@Preview
@Composable
private fun GuildsListLoadedPreview() {
    Column(modifier = Modifier.width(100.dp)) {
        GuildsListLoaded(
            onGuildSelect = {/*TODO Select Guild Event*/ },
            //当前选择的工会ID
            selectedGuildId = 3,
            guilds = listOf(
                DomainGuild(
                    id = 1,
                    name = "The Legend of Zelda",
                    iconUrl = "https://qiniu.yyin.top/zelda.png",
                    bannerUrl = "",
                    permissions = listOf(DomainPermission.NONE),
                    premiumTier = 1,
                    premiumSubscriptionCount = 1
                ),
                DomainGuild(
                    id = 2,
                    name = "The Legend of Link",
                    iconUrl = "https://qiniu.yyin.top/link.png",
                    bannerUrl = "",
                    permissions = listOf(DomainPermission.NONE),
                    premiumTier = 1,
                    premiumSubscriptionCount = 1
                ),
                DomainGuild(
                    id = 3,
                    name = "Cyberpunk 2077 EdgeRunner Lucy fans",
                    iconUrl = "https://qiniu.yyin.top/lucy.png",
                    bannerUrl = "",
                    permissions = listOf(DomainPermission.NONE),
                    premiumTier = 1,
                    premiumSubscriptionCount = 1
                ),
                DomainGuild(
                    id = 4,
                    name = "Lucy",
                    iconUrl = null,
                    bannerUrl = "",
                    permissions = listOf(DomainPermission.NONE),
                    premiumTier = 1,
                    premiumSubscriptionCount = 1
                )
            )
        )
    }

}

@Preview
@Composable
private fun ChannelsListLoadingPreview() {

    Row() {
        GuildsListLoadedPreview()
        //ChannelsListLoading()
        ChannelsListLoaded(
            onChannelSelect = {},
            onCategoryClick = {},
            selectedChannelId = 1,
            bannerUrl = "https://qiniu.yyin.top/sexyLucy.jpg",
            boostLevel = 0,
            guildName = "Lucy group",
            /**Map<DomainChannel.Category?, List<DomainChannel>>**/
            channels =
            mapOf(
                DomainChannel.Category(
                    id = 1,
                    guildId = 3,
                    name = "INFO",
                    position = 1,
                    permissions = listOf(DomainPermission.NONE),
                )
                        to listOf<DomainChannel>(
                    DomainChannel.TextChannel(
                        id = 1,
                        guildId = 3,
                        name = "rule",
                        position = 1,
                        permissions = listOf(DomainPermission.NONE),
                        nsfw = false,
                        parentId = null
                    ),
                    DomainChannel.TextChannel(
                        id = 2,
                        guildId = 3,
                        name = "welcome",
                        position = 1,
                        permissions = listOf(DomainPermission.NONE),
                        nsfw = false,
                        parentId = null
                    )

                ),
                DomainChannel.Category(
                    id = 2,
                    guildId = 3,
                    name = "GENERAL",
                    position = 1,
                    permissions = listOf(DomainPermission.NONE),
                )
                        to listOf<DomainChannel>(
                    DomainChannel.TextChannel(
                        id = 3,
                        guildId = 3,
                        name = "general",
                        position = 1,
                        permissions = listOf(DomainPermission.NONE),
                        nsfw = false,
                        parentId = null
                    ),
                    DomainChannel.TextChannel(
                        id = 4,
                        guildId = 3,
                        name = "welcome",
                        position = 1,
                        permissions = listOf(DomainPermission.NONE),
                        nsfw = false,
                        parentId = 3L
                    )

                ),
                DomainChannel.Category(
                    id = 2,
                    guildId = 3,
                    name = "GENERAL",
                    position = 1,
                    permissions = listOf(DomainPermission.NONE),
                )
                        to listOf<DomainChannel>(
                    DomainChannel.TextChannel(
                        id = 3,
                        guildId = 3,
                        name = "general",
                        position = 1,
                        permissions = listOf(DomainPermission.NONE),
                        nsfw = false,
                        parentId = null
                    ),
                    DomainChannel.TextChannel(
                        id = 4,
                        guildId = 3,
                        name = "welcome",
                        position = 1,
                        permissions = listOf(DomainPermission.NONE),
                        nsfw = false,
                        parentId = 3L
                    )

                ),
                DomainChannel.Category(
                    id = 3,
                    guildId = 3,
                    name = "VOICE CHANNELS",
                    position = 1,
                    permissions = listOf(DomainPermission.NONE),
                )
                        to listOf<DomainChannel>(
                    DomainChannel.VoiceChannel(
                        id = 5 ,
                        guildId = 3,
                        name = "emo topic",
                        position = 1,
                        permissions = listOf(DomainPermission.NONE),
                        parentId = null
                    ),
                    DomainChannel.VoiceChannel(
                        id = 6,
                        guildId = 3,
                        name = "cyber chat",
                        position = 1,
                        permissions = listOf(DomainPermission.NONE),
                        parentId = 3L
                    )

                ),

            ),
            collapsedCategories = listOf(0L),
        )
    }

}