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

//制造一个存放DomainGuild的map，并制造假信息
fun makeFakeInfo(
): Map<Long, DomainGuild> {
    //制造假信息
    val map = mutableMapOf<Long,DomainGuild>()
    //向map中添加假信息

    map[0]=DomainGuild(1,"linux","https://qiniu.yyin.top/linux.png","https://qiniu.yyin.top/20220407190053.png", listOf(),1,1)
    return map
}


