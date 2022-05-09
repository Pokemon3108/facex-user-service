package by.daryazaleuskaya.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtConfigurer @Autowired constructor(
    private val tokenFilter : TokenFilter
) {

    /**
     * {@inheritDoc}
     */
    fun configure(http: HttpSecurity) {
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}