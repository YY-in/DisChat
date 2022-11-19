package com.yyin.dischat.gateway.event

import com.yyin.dischat.rest.dto.ApiGuildMemberChunk

data class GuildMemberChunkEvent(
    val data: ApiGuildMemberChunk
) : Event
