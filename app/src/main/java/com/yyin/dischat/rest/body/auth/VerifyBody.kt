package com.yyin.dischat.rest.body.auth

import kotlinx.serialization.Serializable

@Serializable
data class VerifyBody(
    val phone:String?,
    val email:String?,
    val code:String?=null
)

@Serializable
data class VerifyPhoneBody(
    val phone : String,
    val code : String
)

@Serializable
data class VerifyEmailBody(
    val email : String,
    val code : String
)
