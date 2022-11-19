package com.yyin.dischat.gateway.dto

import com.yyin.dischat.rest.dto.ApiActivity
import kotlinx.serialization.Serializable

@Serializable
data class UpdatePresence(
    val since: Long,
    val status: String,
    val afk: Boolean?,
    val activities: List<ApiActivity>,
)
