package com.tinkoff.monitoringAccount.service.client

import com.tinkoff.monitoringAccount.entity.UserEmail
import com.tinkoff.monitoringAccount.model.CompEmailResponse
import com.tinkoff.monitoringAccount.model.LeakSourceRequest
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class CheckEmailClient(private val webClient: WebClient) {

    suspend fun checkEmail(userEmail: UserEmail) = webClient
        .get()
        .uri("/email/${userEmail.address!!}")
        .retrieve()
        .bodyToMono(CompEmailResponse::class.java)
        .awaitSingleOrNull()

    suspend fun checkEmailBySource(userEmail: UserEmail, leakSourceRequest: LeakSourceRequest) = webClient
        .get()
        .uri("/email/${userEmail.address!!}/${leakSourceRequest.source}")
        .retrieve()
        .bodyToMono(CompEmailResponse::class.java)
        .awaitSingleOrNull()

}