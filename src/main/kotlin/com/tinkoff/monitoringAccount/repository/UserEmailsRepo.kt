package com.tinkoff.monitoringAccount.repository

import com.tinkoff.monitoringAccount.entity.User
import com.tinkoff.monitoringAccount.entity.UserEmail
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserEmailsRepo : CrudRepository<UserEmail, Long> {

    fun findAllByUser(user: User): Iterable<UserEmail>

    fun findByAddress(name: String?): UserEmail?
}