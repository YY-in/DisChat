package com.xinto.opencord.gateway.event

import com.yyin.dischat.gateway.dto.ReplaceSessionsData
import com.yyin.dischat.gateway.event.Event

data class SessionsReplaceEvent(
    val data: ReplaceSessionsData,
) : Event
