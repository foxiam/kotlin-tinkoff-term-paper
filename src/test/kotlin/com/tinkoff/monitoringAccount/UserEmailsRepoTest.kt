package com.tinkoff.monitoringAccount

import com.tinkoff.monitoringAccount.entity.User
import com.tinkoff.monitoringAccount.entity.UserEmail
import com.tinkoff.monitoringAccount.repository.UserEmailsRepo
import com.tinkoff.monitoringAccount.repository.UserRepo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserEmailsRepoTest {
    @Autowired
    private lateinit var userRepo: UserRepo

    @Autowired
    private lateinit var userEmailsRepo: UserEmailsRepo

    private val user = User(username = "userEmailRepoTest@gmail.com", password = "123")
    private val email = UserEmail(address = "userEmailRepoTest@mail.ru", user = user)

    @BeforeEach
    fun setUp() {
        userRepo.save(user)
    }

    @Test
    fun `add and get email test`() {
        userEmailsRepo.save(email)
        val userEmailFromDB = userEmailsRepo.findByAddress(email.address)
        assertEquals(email.address, userEmailFromDB?.address)
    }
}