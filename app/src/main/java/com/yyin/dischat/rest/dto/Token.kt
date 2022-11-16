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
@Serializable
data class BaseResponse(
    @SerialName("code")
    val code :Int,
    @SerialName("message")
    val message :String
)

@Serializable
data class Response<T>(
    @SerialName("code")
    val code :Int,
    @SerialName("data")
    val data :T,
    @SerialName("message")
    val message :String?,
    @SerialName("error")
    val error :String?
)