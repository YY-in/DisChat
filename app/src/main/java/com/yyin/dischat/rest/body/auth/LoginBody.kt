package com.yyin.dischat.rest.body.auth
import kotlinx.serialization.Serializable

@Serializable
data class LoginBody(
    val email: String?,
    val phone: String?,
    val password : String,
)
