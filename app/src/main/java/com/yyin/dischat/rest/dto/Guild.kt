package com.yyin.dischat.rest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiGuild(
    @SerialName("id")
    val id: ApiSnowflake,

    @SerialName("name")
    val name: String,

    @SerialName("icon")
    val icon: String?,

    @SerialName("banner")
    val banner: String? = null,

    @SerialName("permissions")
    val permissions: ApiPermissions = ApiPermissions(0),

    @SerialName("premium_tier")
    val premiumTier: Int,

    @SerialName("premium_subscription_count")
    val premiumSubscriptionCount: Int? = null
)


@Serializable
data class ApiMeGuild(
    @SerialName("id")
    val id: ApiSnowflake,

    @SerialName("name")
    val name: String,

    @SerialName("icon")
    val icon: String?,

    @SerialName("permissions")
    val permissions: ApiPermissions = ApiPermissions(0),
)