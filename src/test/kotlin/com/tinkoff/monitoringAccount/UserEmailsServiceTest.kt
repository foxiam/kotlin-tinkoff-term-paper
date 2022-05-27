package com.tinkoff.monitoringAccount

import com.tinkoff.monitoringAccount.entity.User
import com.tinkoff.monitoringAccount.entity.UserEmail
import com.tinkoff.monitoringAccount.repository.UserEmailsRepo
import com.tinkoff.monitoringAccount.repository.UserRepo
import com.tinkoff.monitoringAccount.service.UserEmailsService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class UserEmailsServiceTest {
    @Autowired
    lateinit var userRepo: UserRepo

    @Autowired
    lateinit var userEmailsRepo: UserEmailsRepo

    @Autowired
    lateinit var userEmailsService: UserEmailsService

    private val user = User(username = "userEmailsServiceTest@gmail.com", password = "123")
    private val email = UserEmail(address = "userEmailsServiceTest@mail.ru")
    private val email2 = UserEmail(address = "userEmailsServiceTest2@mail.ru")

    @BeforeEach
    fun setUp() {
        userRepo.save(user)
        userEmailsRepo.save(email2)
    }

    @Test
    fun `add email test`() {
        val response = userEmailsService.addUserEmails(user, email)
        assertEquals(0, response)
    }

    @Test
    fun `add email exist test`() {
        val userFromDB = userRepo.findByUsername(user.username)
        val response = userEmailsService.addUserEmails(userFromDB!!, email2)
        assertEquals(1, response)
    }
}