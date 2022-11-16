package com.yyin.dischat.rest.body.auth
import kotlinx.serialization.Serializable

data class LoginBody(
    val email: String?,
    val phone: String?,
    val password : String,
)

@Serializable
data class LoginPhoneBody(
    val phone: String?,
    val password : String,
)


@Serializable
data class LoginEmailBody(
    val email: String?,
    val password : String,
)