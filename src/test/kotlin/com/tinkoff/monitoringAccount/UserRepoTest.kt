package com.tinkoff.monitoringAccount

import com.tinkoff.monitoringAccount.entity.User
import com.tinkoff.monitoringAccount.repository.UserRepo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepoTest {
    @Autowired
    private lateinit var userRepo: UserRepo

    private val user = User(username = "userRepoTest@gmail.com", password = "123")
    private val user2 = User(username = "userRepoTest2@gmail.com", password = "123")

    @BeforeEach
    fun setUp() {
        userRepo.save(user)
    }

    @Test
    fun `add user test`() {
        userRepo.save(user2)

        val userFormDB = userRepo.findByUsername(user2.username)
        assertEquals(user2.username, userFormDB?.username)
        assertEquals(user2.password, userFormDB?.password)
        assertEquals(user2.roles, userFormDB?.roles)
    }

    @Test
    fun `get user test`() {
        val userFormDB = userRepo.findByUsername(user.username)
        assertEquals(user.username, userFormDB?.username)
        assertEquals(user.password, userFormDB?.password)
        assertEquals(user.roles, userFormDB?.roles)
    }

}