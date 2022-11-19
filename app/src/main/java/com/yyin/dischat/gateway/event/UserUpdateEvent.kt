package com.yyin.dischat.gateway.event

import com.yyin.dischat.rest.dto.ApiUser

data class UserUpdateEvent(
    val data: ApiUser
) : Event
