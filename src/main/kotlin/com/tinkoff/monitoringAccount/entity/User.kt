package com.tinkoff.monitoringAccount.entity

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.validation.annotation.Validated
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size


@Entity
@Table(name = "users")
@Validated
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Email(message = "Email should be valid")
    @Column(name = "email")
    internal var username: String? = null,

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 4, max = 20, message = "Password should be between 4 and 20 characters")
    internal var password: String? = null,

    var enabled: Boolean = true,

    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")])
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val roles: MutableSet<Role> = mutableSetOf(Role.USER),

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "user")
    val userEmails: Set<UserEmail> = setOf()

) : UserDetails {

    override fun getAuthorities(): MutableCollection<Role> = roles
    override fun getPassword(): String? = password

    override fun getUsername(): String? = username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = enabled
}