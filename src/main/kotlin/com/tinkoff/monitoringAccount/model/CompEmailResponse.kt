package com.tinkoff.monitoringAccount.model

data class CompEmailResponse(
    val address: String?,
    val leakSources: Set<LeakSourceRequest>?
)