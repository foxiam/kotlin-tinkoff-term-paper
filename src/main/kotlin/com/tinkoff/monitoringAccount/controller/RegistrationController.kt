package com.tinkoff.monitoringAccount.controller

import com.tinkoff.monitoringAccount.entity.User
import com.tinkoff.monitoringAccount.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid


@Controller
class RegistrationController(
    @Autowired private val userService: UserService
) {


    @GetMapping("/registration")
    fun registration(): String {
        return "registration"
    }

    @PostMapping("/registration")
    fun addUser(
        @Valid user: User,
        model: MutableMap<String, Any>
    ): String {
        when (userService.addUser(user)) {
            1 -> model["message"] = "User exists"
            else -> return "redirect:/login"
        }

        return "registration"
    }
}