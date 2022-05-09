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

    private val TOKEN_EXPIRED = "token.expired"

    private val REFRESH_TOKEN_LIFE_TIME = 1440L

    override fun findByToken(token: String): RefreshTokenDto {

        return refreshTokenRepository.findByToken(token)?.toRefreshTokenDto()
            ?: throw RefreshTokenException(token, NO_REFRESH_TOKEN)
    }

    override fun createRefreshTokenIfNotExists(username: String): RefreshTokenDto {

        val existingToken = refreshTokenRepository.findByUserLogin(username)?.toRefreshTokenDto()
        return existingToken ?: createToken(username)
    }

    override fun validateToken(tokenDto: RefreshTokenDto, user: SystemUserDto) {

        if (tokenDto.user == user) {
            throw RefreshTokenException(tokenDto.token, NO_REFRESH_TOKEN)
        }
        deleteTokenIfExpires(tokenDto)
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

    /**
     * Delete token if it has been expired
     *
     * @param token - to be checked and probably deleted
     */
    private fun deleteTokenIfExpires(tokenDto: RefreshTokenDto) {

        if (tokenDto.expireDate.before(Date())) {
            refreshTokenRepository.delete(tokenDto.toRefreshTokenDataModel())
            throw RefreshTokenException(TOKEN_EXPIRED, tokenDto.token)
        }
    }
}