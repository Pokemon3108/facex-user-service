package by.daryazaleuskaya.dto

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SystemUserDto(

    val id: String?,
    val login: String,
    private val password: String,
    val authorities: Set<String>,

    val name: String,
    val surname: String,
    val email: String,
    val company: String
) : UserDetails {

    override fun getAuthorities() = authorities.map { SimpleGrantedAuthority(it) }

    override fun getPassword() = password

    override fun getUsername() = login

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}