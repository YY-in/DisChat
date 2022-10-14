package com.yyin.dischat.util

import com.yyin.dischat.domain.model.DomainChannel

/**
 * @return 包含频道列表的目录.
 * 按照: 频道规则, 频道公告, 文字频道, 阶段, 语音频道 分类
 */
fun getSortedChannels(channels: Collection<DomainChannel>): Map<DomainChannel.Category?, List<DomainChannel>> {
    val categories = channels.filterIsInstance<DomainChannel.Category>().sortedBy { it.position }
    val nonCategories = channels.filter { it !is DomainChannel.Category }
        .sortedWith(compareBy({ it }, { it.position }))

    val sortedChannels = mutableMapOf<DomainChannel.Category?, List<DomainChannel>>(
        null to nonCategories.filter {
            it.parentId == null
        }
    )

    categories.forEach { category ->
        sortedChannels[category] = nonCategories.filter {
            category.id == it.parentId
        }
    }

    return sortedChannels
}