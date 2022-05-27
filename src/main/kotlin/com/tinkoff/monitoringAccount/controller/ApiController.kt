package com.tinkoff.monitoringAccount.controller

import com.tinkoff.monitoringAccount.model.LeakSourceRequest
import com.tinkoff.monitoringAccount.service.CheckEmailService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class ApiController(
    val checkEmailService: CheckEmailService
) {

    @PostMapping("/receive-source")
    fun receiveNewLeak(@RequestBody leakSourceRequest: LeakSourceRequest): LeakSourceRequest {
        checkEmailService.checkAllEmail(leakSourceRequest)
        return leakSourceRequest
    }

}