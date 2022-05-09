package by.daryazaleuskaya.controller

import by.daryazaleuskaya.RefreshTokenService
import by.daryazaleuskaya.dto.SystemUserDto
import by.daryazaleuskaya.security.JwtResponse
import by.daryazaleuskaya.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/v1/auth")
class AuthController @Autowired constructor(
    private val jwtTokenProvider: JwtTokenProvider,
    private val authenticationManager: AuthenticationManager,
    private val refreshTokenService : RefreshTokenService
) {

    @PostMapping("/login")
    fun login(@RequestBody authData: SystemUserDto): JwtResponse {
        val username = authData.username
        val password = authData.password
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
        val jwt = jwtTokenProvider.createToken(username)
        val refreshToken = refreshTokenService.createRefreshTokenIfNotExists(username)
        return JwtResponse(username, jwt, refreshToken.token, jwtTokenProvider.tokenLifeTime)
    }

    @PostMapping("/refreshToken")
    fun refreshToken(request: HttpServletRequest, @RequestBody userDto: SystemUserDto): JwtResponse {
        val requestRefreshToken = request.getAttribute("token").toString()
        val tokenDto = refreshTokenService.findByToken(requestRefreshToken)
        refreshTokenService.validateToken(tokenDto, userDto)
        val newTokenDto = refreshTokenService.updateToken(tokenDto)
        val username: String = tokenDto.user.username
        val token = jwtTokenProvider.createToken(username)
        return JwtResponse(username, token, newTokenDto.token, jwtTokenProvider.tokenLifeTime)
    }

}