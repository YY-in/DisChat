package com.yyin.dischat.gateway.event

import com.yyin.dischat.rest.dto.ApiGuild

data class GuildCreateEvent(
    val data: ApiGuild
): Event

data class GuildUpdateEvent(
    val data: ApiGuild
): Event

