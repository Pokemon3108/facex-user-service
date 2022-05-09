package by.daryazaleuskaya.security

import by.daryazaleuskaya.RefreshTokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component


@Component
class RefreshTokenProvider @Autowired constructor(
    @Lazy private val refreshTokenService: RefreshTokenService,
    @Qualifier("systemUserServiceImpl") private val userService: UserDetailsService
) {

    fun getUsername(token: String): String {
        val tokenDto = refreshTokenService.findByToken(token)
        return tokenDto.user.login
    }

    fun getAuthentication(token: String): Authentication {

        val userDetails = userService.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

}