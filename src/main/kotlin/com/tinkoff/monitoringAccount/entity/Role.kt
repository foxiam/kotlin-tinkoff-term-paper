package com.tinkoff.monitoringAccount.entity

import org.springframework.security.core.GrantedAuthority

enum class Role: GrantedAuthority {
    USER;

    override fun getAuthority(): String {
        return name
    }
}