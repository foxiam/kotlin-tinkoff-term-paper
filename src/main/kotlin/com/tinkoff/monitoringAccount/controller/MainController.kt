package com.tinkoff.monitoringAccount.controller

import com.tinkoff.monitoringAccount.entity.User
import com.tinkoff.monitoringAccount.entity.UserEmail
import com.tinkoff.monitoringAccount.service.UserEmailsService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid


@Controller
class MainController(val userEmailsService: UserEmailsService) {

    @GetMapping
    fun getAllUserEmails(
        @AuthenticationPrincipal user: User,
        model: MutableMap<String, Any>
    ): String {
        model["email"] = userEmailsService.getAllUserEmailsByUser(user)
        return "main"
    }

    @PostMapping
    fun addUserEmails(
        @AuthenticationPrincipal user: User,
        @Valid userEmail: UserEmail,
        model: MutableMap<String, Any>
    ): String {
        when (userEmailsService.addUserEmails(user, userEmail)) {
            1 -> model["message"] = "Email exists"
        }
        model["email"] = userEmailsService.getAllUserEmailsByUser(user)
        return "main"
    }

    @PostMapping("/delete")
    fun deleteUserEmail(
        @AuthenticationPrincipal user: User,
        @RequestParam(value = "address") address: String,
        model: MutableMap<String, Any>
    ): String {
        userEmailsService.deleteUserEmail(user, address)
        model["email"] = userEmailsService.getAllUserEmailsByUser(user)
        return "redirect:/"
    }
}