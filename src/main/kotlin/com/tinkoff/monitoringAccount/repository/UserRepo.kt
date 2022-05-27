package com.tinkoff.monitoringAccount.repository

import com.tinkoff.monitoringAccount.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo : JpaRepository<User, Long> {

    fun findByUsername(username: String?): User?

}