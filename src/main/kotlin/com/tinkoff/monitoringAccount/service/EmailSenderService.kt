package com.tinkoff.monitoringAccount.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailSenderService (
    private val javaMailSender: JavaMailSender
) {

    suspend fun sendSimpleEmail(toAddress: String, subject: String, message: String) {
        val simpleMailMessage = SimpleMailMessage()
        simpleMailMessage.setTo(toAddress)
        simpleMailMessage.subject = subject
        simpleMailMessage.text = message
        javaMailSender.send(simpleMailMessage)
    }

}