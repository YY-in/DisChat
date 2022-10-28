package com.yyin.dischat.domain.model

sealed interface DomainLogin {

    data class Login(
        val token: String
    ) : DomainLogin

    object Error : DomainLogin

}