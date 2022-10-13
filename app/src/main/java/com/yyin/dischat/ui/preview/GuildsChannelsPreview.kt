package com.yyin.dischat.ui.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyin.dischat.domain.model.DomainCustomStatus
import com.yyin.dischat.domain.model.DomainGuild
import com.yyin.dischat.domain.model.DomainPermission
import com.yyin.dischat.domain.model.DomainUserStatus
import com.yyin.dischat.ui.screen.home.CurrentUserItemLoaded
import com.yyin.dischat.ui.screen.home.CurrentUserItemLoading
import com.yyin.dischat.ui.screen.home.GuildsListLoaded


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
    Column(modifier= Modifier.width(100.dp)){
        GuildsListLoaded(
            onGuildSelect = {/*TODO Select Guild Event*/},
            selectedGuildId = 2,
            guilds= listOf(
                DomainGuild(
                    id = 1,
                    name = "The Legend of Zelda",
                    iconUrl = "https://qiniu.yyin.top/zelda.png",
                    bannerUrl= "",
                    permissions= listOf(DomainPermission.NONE),
                    premiumTier= 1,
                    premiumSubscriptionCount= 1
                ),
                DomainGuild(
                    id = 2,
                    name = "The Legend of Link",
                    iconUrl = "https://qiniu.yyin.top/link.png",
                    bannerUrl= "",
                    permissions= listOf(DomainPermission.NONE),
                    premiumTier= 1,
                    premiumSubscriptionCount= 1
                ),
                DomainGuild(
                    id = 3,
                    name = "Cyberpunk 2077 EdgeRunner Lucy fans",
                    iconUrl = "https://qiniu.yyin.top/lucy.png",
                    bannerUrl= "",
                    permissions= listOf(DomainPermission.NONE),
                    premiumTier= 1,
                    premiumSubscriptionCount= 1
                ),
                DomainGuild(
                    id = 4,
                    name = "Lucy fans",
                    iconUrl = null,
                    bannerUrl= "",
                    permissions= listOf(DomainPermission.NONE),
                    premiumTier= 1,
                    premiumSubscriptionCount= 1
                )
            )
        )
    }

}


