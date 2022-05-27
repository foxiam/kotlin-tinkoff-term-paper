package com.tinkoff.monitoringAccount.service

import com.tinkoff.monitoringAccount.entity.User
import com.tinkoff.monitoringAccount.repository.UserRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepo: UserRepo,
    private val passwordEncoder: PasswordEncoder
): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails? =
        userRepo.findByUsername(username)

    fun addUser(user: User): Int {
        val userFromDb: User? = userRepo.findByUsername(user.username)
        if (userFromDb != null) return 1
        CoroutineScope(Dispatchers.Default).launch {
            user.password = passwordEncoder.encode(user.password)
            withContext(Dispatchers.IO) {
                userRepo.save(user)
            }
        }
        return 0
    }

}