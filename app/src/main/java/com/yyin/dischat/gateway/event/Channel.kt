package com.yyin.dischat.gateway.event

import com.yyin.dischat.rest.dto.ApiChannel

data class ChannelCreateEvent(
    val data: ApiChannel,
) : Event

data class ChannelUpdateEvent(
    val data: ApiChannel,
) : Event

data class ChannelDeleteEvent(
    val data: ApiChannel,
) : Event