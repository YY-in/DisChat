package com.yyin.dischat.gateway.dto

import com.yyin.dischat.rest.dto.ApiGuild
import com.yyin.dischat.rest.dto.ApiUser
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ready(
    @SerialName("user")
    val user: ApiUser,

    @SerialName("session_id")
    val sessionId: String,

    @SerialName("guilds")
    val guilds: List<ApiGuild>,

    @SerialName("sessions")
    val sessions: List<SessionData>,
)
