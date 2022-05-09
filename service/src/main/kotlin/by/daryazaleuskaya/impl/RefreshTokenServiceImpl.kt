package by.daryazaleuskaya.impl

import by.daryazaleuskaya.RefreshTokenService
import by.daryazaleuskaya.SystemUserService
import by.daryazaleuskaya.dto.RefreshTokenDto
import by.daryazaleuskaya.dto.SystemUserDto
import by.daryazaleuskaya.exception.RefreshTokenException
import by.daryazaleuskaya.repos.RefreshTokenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class RefreshTokenServiceImpl @Autowired constructor(
    private val systemUserService: SystemUserService,
    private val passwordEncoder: PasswordEncoder,
    private val refreshTokenRepository: RefreshTokenRepository
) : RefreshTokenService {

    private val NO_REFRESH_TOKEN = "no.refresh.token"

    private val REFRESH_TOKEN_LIFE_TIME = 1440L

    override fun findByToken(token: String): RefreshTokenDto {

        return refreshTokenRepository.findByToken(token)?.toRefreshTokenDto()
            ?: throw RefreshTokenException(token, NO_REFRESH_TOKEN)
    }

    override fun createRefreshTokenIfNotExists(username: String): RefreshTokenDto {

        val existingToken = refreshTokenRepository.findByUserLogin(username)?.toRefreshTokenDto()
        return existingToken ?: createToken(username)
    }

    override fun validateToken(token: RefreshTokenDto, user: SystemUserDto?) {
        TODO("Not yet implemented")
    }

    override fun updateToken(tokenDto: RefreshTokenDto): RefreshTokenDto? {
        TODO("Not yet implemented")
    }

    private fun createToken(username: String): RefreshTokenDto {

        val userDto = systemUserService.read(username)
        val exp = Date.from(
            LocalDateTime.now().plusMinutes(REFRESH_TOKEN_LIFE_TIME)
                .atZone(ZoneId.systemDefault()).toInstant()
        )
        val tokenContent = UUID.randomUUID().toString()
        val refreshTokenDto = RefreshTokenDto(user = userDto, token = tokenContent, expireDate = exp)
        refreshTokenRepository.save(refreshTokenDto.toRefreshTokenDataModel())
        return refreshTokenDto
    }
}