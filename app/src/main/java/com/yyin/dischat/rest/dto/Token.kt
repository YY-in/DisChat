package com.yyin.dischat.rest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiUploadToken(
    @SerialName("upload_token")
    val token :String
)

@Serializable
data class ApiLogin(
    @SerialName("token")
    val token :String ?= null
)
