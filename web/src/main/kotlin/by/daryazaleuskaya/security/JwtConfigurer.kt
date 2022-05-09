package by.daryazaleuskaya.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

@Component
class JwtConfigurer @Autowired constructor(
    private val tokenFilter : TokenFilter
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    /**
     * {@inheritDoc}
     */
    override fun configure(http: HttpSecurity) {
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}