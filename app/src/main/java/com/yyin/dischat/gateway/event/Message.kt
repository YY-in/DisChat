package com.yyin.dischat.gateway.event

import com.yyin.dischat.gateway.dto.MessageDeleteData
import com.yyin.dischat.gateway.event.Event
import com.yyin.dischat.rest.dto.ApiMessage
import com.yyin.dischat.rest.dto.ApiMessagePartial

data class MessageCreateEvent(
    val data: ApiMessage,
) : Event

data class MessageUpdateEvent(
    val data: ApiMessagePartial,
) : Event

data class MessageDeleteEvent(
    val data: MessageDeleteData,
) : Event