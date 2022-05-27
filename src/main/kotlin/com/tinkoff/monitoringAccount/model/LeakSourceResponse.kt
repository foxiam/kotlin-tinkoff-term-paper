package com.tinkoff.monitoringAccount.model

data class LeakSourceResponse(
    val source: String?,
    val date: String?,
    val compEmail: Set<CompEmailRequest>?
)