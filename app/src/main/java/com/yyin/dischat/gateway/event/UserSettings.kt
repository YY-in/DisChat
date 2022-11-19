package com.yyin.dischat.gateway.event

import com.yyin.dischat.rest.dto.ApiUserSettingsPartial


data class UserSettingsUpdateEvent(
    val data: ApiUserSettingsPartial
) : Event