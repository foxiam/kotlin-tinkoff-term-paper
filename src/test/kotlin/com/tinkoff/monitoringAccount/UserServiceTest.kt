package com.tinkoff.monitoringAccount

import com.tinkoff.monitoringAccount.entity.User
import com.tinkoff.monitoringAccount.repository.UserRepo
import com.tinkoff.monitoringAccount.service.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest {
    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userRepo: UserRepo

    private val user = User(username = "userServiceTest.gmail.com", password = "123")
    private val user2 = User(username = "userServiceTest2.gmail.com", password = "123")

    @BeforeEach
    fun setUp() {
        userRepo.save(user2)
    }

    @Test
    fun `add user test`() {
        val response = userService.addUser(user)
        assertEquals(0, response)
    }

    @Test
    fun `add user exist test`() {
        val response = userService.addUser(user2)
        assertEquals(1, response)
    }

}