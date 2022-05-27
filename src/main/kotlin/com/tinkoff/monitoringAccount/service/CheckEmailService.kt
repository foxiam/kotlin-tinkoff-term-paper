package com.tinkoff.monitoringAccount.service

import com.tinkoff.monitoringAccount.entity.UserEmail
import com.tinkoff.monitoringAccount.model.LeakSourceRequest
import com.tinkoff.monitoringAccount.repository.UserEmailsRepo
import com.tinkoff.monitoringAccount.service.client.CheckEmailClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class CheckEmailService(
    private val userEmailsRepo: UserEmailsRepo,
    private val emailSenderService: EmailSenderService,
    private val checkEmailClient: CheckEmailClient
) {

    fun checkEmail(userEmail: UserEmail) {
        CoroutineScope(Dispatchers.Default).launch {
            val compEmail = checkEmailClient.checkEmail(userEmail)
            compEmail?.let {
                createMessage(
                    userEmail.user!!.username!!,
                    compEmail.address!!,
                    compEmail.leakSources!!
                        .joinToString(separator = "\n") {
                            "   ${it.source!!} ${it.date!!}"
                        }
                )
            }
        }
    }

    fun checkAllEmail(leakSourceRequest: LeakSourceRequest) {
        CoroutineScope(Dispatchers.Default).launch {
            val allUserEmail = withContext(Dispatchers.IO) {
                userEmailsRepo.findAll()
            }
            allUserEmail.forEach { userEmail ->
                val emailFromCompEmailService = checkEmailClient
                    .checkEmailBySource(userEmail, leakSourceRequest)
                emailFromCompEmailService?.let { compEmail ->
                    createMessage(
                        userEmail.user!!.username!!,
                        compEmail.address!!,
                        "${leakSourceRequest.source} ${leakSourceRequest.date}"
                    )
                }
            }
        }
    }

    private suspend fun createMessage(username: String, address: String, leakSource: String) =
        emailSenderService
            .sendSimpleEmail(
                username,
                "Компроментация аккаунта",
                "Email: ${address}\n" +
                        "Утечка данных:\n" +
                        leakSource
            )
}