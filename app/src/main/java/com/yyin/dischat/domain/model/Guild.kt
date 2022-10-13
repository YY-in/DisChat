package com.yyin.dischat.domain.model

data class DomainGuild(
    val id: Long,
    val name: String,
    val iconUrl: String?,
    val bannerUrl: String?,
    val permissions: List<DomainPermission>,
    val premiumTier: Int,
    val premiumSubscriptionCount: Int,
){
    val iconText = name
        .split(ICON_TEXT_REGEX)
        .map { it[0] }
        .joinToString()

    companion object {
        val ICON_TEXT_REGEX = """\s+""".toRegex()
    }
}