package by.daryazaleuskaya.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import java.util.stream.Collectors
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Component
@PropertySource("classpath:application.properties")
open class JwtTokenProvider @Autowired constructor(
    @Qualifier("systemUserServiceImpl") private val userService: UserDetailsService
) {

    private val TOKEN_PREFIX = "Bearer "

    private val AUTH_HEADER = "Authorization"

    @Value("\${security.jwt.token.expire-length-min}")
    val tokenLifeTime: Long = 240L

    @Value("\${security.jwt.token.secret-key}")
    private lateinit var secretKey: String

    @PostConstruct
    protected fun init() {

        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }

    open fun createToken(username: String?): String {
        val claims = Jwts.claims().setSubject(username)
        val userDetails = userService.loadUserByUsername(username)
        val roles = userDetails.authorities
            .stream()
            .map { obj: Any -> obj.toString() }
            .collect(Collectors.toList())
        claims["roles"] = roles
        val now = Date()
        val exp = Date.from(
            LocalDateTime.now().plusMinutes(tokenLifeTime)
                .atZone(ZoneId.systemDefault()).toInstant()
        )
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(exp)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    open fun loadToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTH_HEADER)
        return if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            bearerToken.substring(TOKEN_PREFIX.length)
        } else null
    }

    open fun validateToken(token: String?): Boolean {
        val claims = Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
        val now = Date()
        return claims.body.expiration.after(now)
    }

    open fun getUsername(token: String?): String? {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body
            .subject
    }

    open fun getAuthentication(token: String?): Authentication? {
        val userDetails = userService.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(
            userDetails.username, userDetails.password,
            userDetails.authorities
        )
    }
}