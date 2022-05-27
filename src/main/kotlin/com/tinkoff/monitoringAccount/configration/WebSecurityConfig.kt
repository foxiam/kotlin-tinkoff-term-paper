package com.tinkoff.monitoringAccount.configration

import com.tinkoff.monitoringAccount.service.UserService
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    private val userService: UserService,
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers("/registration", "/api/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .permitAll()
            .and()
            .csrf().ignoringAntMatchers("/api/**")

    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService)
            .passwordEncoder(BCryptPasswordEncoder())
    }

}