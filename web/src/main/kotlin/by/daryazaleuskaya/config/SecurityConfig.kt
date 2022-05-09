package by.daryazaleuskaya.config

import by.daryazaleuskaya.controller.handler.ExceptionHandlerFilter
import by.daryazaleuskaya.security.JwtConfigurer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.logout.LogoutFilter

@Configuration
@EnableWebSecurity
open class SecurityConfig @Autowired constructor(
    private val userService : UserDetailsService,
    private val handlerFilter : ExceptionHandlerFilter,
    private val jwtConfigurer: JwtConfigurer,
    private val authPoint : AuthenticationEntryPoint,
    private val accessDeniedHandler : AccessDeniedHandler
) : WebSecurityConfigurerAdapter() {

    @Bean
    open fun passwordEncoder(): PasswordEncoder {

        return BCryptPasswordEncoder()
    }

    override fun configure(http: HttpSecurity) {

        http
            .csrf().disable()
            .addFilterBefore(handlerFilter, LogoutFilter::class.java)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/v1/detection", "/api/v1/recognition/*", "/api/v1/statistic/*").authenticated()
            .antMatchers("/api/v1/face/*", "/api/v1/auth/refreshToken").authenticated()
            .antMatchers(HttpMethod.POST,"/api/v1/user", "/api/v1/auth").hasAnyRole("ANONYMOUS", "ADMIN")

            .anyRequest()
            .hasRole("ADMIN")

            .and()
            .exceptionHandling()
            .authenticationEntryPoint(authPoint)
            .accessDeniedHandler(accessDeniedHandler)

            .and()
            .apply(jwtConfigurer)

    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {

        auth.userDetailsService(userService).passwordEncoder(passwordEncoder())
    }

    @Bean
    @Throws(java.lang.Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {

        return super.authenticationManagerBean()
    }
}