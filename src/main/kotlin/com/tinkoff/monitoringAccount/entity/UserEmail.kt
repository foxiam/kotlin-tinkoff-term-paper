package com.tinkoff.monitoringAccount.entity

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "user_emails")
class UserEmail(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    var address: String? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    var user: User? = null
)