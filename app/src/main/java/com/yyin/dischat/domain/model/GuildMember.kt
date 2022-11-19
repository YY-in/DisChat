package com.yyin.dischat.domain.model

data class DomainGuildMember(
    val user: DomainUser?,
    val nick: String? = null,
    val avatarUrl: String? = null,
)
