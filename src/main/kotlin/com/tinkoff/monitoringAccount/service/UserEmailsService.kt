package com.tinkoff.monitoringAccount.service

import com.tinkoff.monitoringAccount.entity.User
import com.tinkoff.monitoringAccount.entity.UserEmail
import com.tinkoff.monitoringAccount.repository.UserEmailsRepo
import org.springframework.stereotype.Service

@Service
class UserEmailsService(
    private val userEmailsRepo: UserEmailsRepo,
    private val checkEmailService: CheckEmailService,
    ) {

    fun addUserEmails(user: User, userEmail: UserEmail): Int {
        val userEmailFromDb: UserEmail? = userEmailsRepo.findByAddress(userEmail.address)
        if (userEmailFromDb != null) {
            return 1
        }

        userEmail.user = user
        userEmailsRepo.save(userEmail)
        checkEmailService.checkEmail(userEmail)

        return 0
    }

    fun deleteUserEmail(user: User, address: String) {
        val userEmail = userEmailsRepo.findByAddress(address)
        userEmail?.let {
            if (it.user!!.id == user.id) {
                userEmailsRepo.delete(it)
            }
        }
    }

    fun getAllUserEmailsByUser(user: User) =
        userEmailsRepo.findAllByUser(user)

    fun getAllUserEmails(): MutableIterable<UserEmail> =
        userEmailsRepo.findAll()
}