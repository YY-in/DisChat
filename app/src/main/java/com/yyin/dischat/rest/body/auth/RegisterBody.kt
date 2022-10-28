package com.yyin.dischat.rest.body.auth

import kotlinx.serialization.Serializable

@Serializable
data class RegisterBody(
    val username:String,
    val password: String,
    val email: String?,
    val phone : String?,
    val avatarUrl : String,
    val verified : Boolean
)
