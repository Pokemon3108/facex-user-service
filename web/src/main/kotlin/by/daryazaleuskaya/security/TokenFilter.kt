package by.daryazaleuskaya.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenFilter @Autowired constructor(
    private val jwtTokenProvider: JwtTokenProvider,
    private val refreshTokenProvider: RefreshTokenProvider
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val token = jwtTokenProvider.loadToken(request)
        request.setAttribute("token", token)

        val grantTypeHeader = request.getHeader("grant-type")

        if (grantTypeHeader != null && grantTypeHeader == "refresh_token") {
            val auth = refreshTokenProvider.getAuthentication(token!!)
            SecurityContextHolder.getContext().authentication = auth
        } else if (token != null && jwtTokenProvider.validateToken(token)) {
            val auth = jwtTokenProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = auth
        }

        filterChain.doFilter(request, response)
    }
}