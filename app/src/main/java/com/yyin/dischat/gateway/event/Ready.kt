package com.yyin.dischat.gateway.event

import com.yyin.dischat.gateway.dto.Ready

data class ReadyEvent(
    val data: Ready
) : Event