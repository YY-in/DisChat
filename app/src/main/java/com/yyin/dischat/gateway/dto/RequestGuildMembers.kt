package com.yyin.dischat.gateway.dto

import com.yyin.dischat.rest.dto.ApiSnowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestGuildMembers(
    @SerialName("guild_id")
    val guildId: ApiSnowflake,

    @SerialName("query")
    val query: String = "",

    @SerialName("limit")
    val limit: Int = 0,

    @SerialName("presences")
    val presences: Boolean? = null,

    @SerialName("user_ids")
    val userIds: List<Long>? = null,
)
