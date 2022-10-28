package com.yyin.dischat.domain.mapper

import com.yyin.dischat.domain.model.DomainLogin
import com.yyin.dischat.rest.dto.ApiLogin

fun ApiLogin.toDomain(): DomainLogin {
    return when {
        token != null -> {
            DomainLogin.Login(
                token = token
            )
        }
        else -> DomainLogin.Error
    }
}