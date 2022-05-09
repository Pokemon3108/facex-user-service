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

@Service
class RefreshTokenServiceImpl @Autowired constructor(
    private val systemUserService : SystemUserService,
    private val passwordEncoder : PasswordEncoder,
    private val refreshTokenRepository: RefreshTokenRepository
) : RefreshTokenService {

    private val NO_REFRESH_TOKEN = "no.refresh.token"

    override fun findByToken(token: String): RefreshTokenDto {

        return refreshTokenRepository.findByToken(token)?.toRefreshTokenDto()
            ?: throw RefreshTokenException(token, NO_REFRESH_TOKEN)
    }

    override fun createRefreshToken(username: String): RefreshTokenDto {
        TODO("Not yet implemented")
    }

    override fun validateToken(token: RefreshTokenDto, user: SystemUserDto?) {
        TODO("Not yet implemented")
    }

    override fun updateToken(tokenDto: RefreshTokenDto): RefreshTokenDto? {
        TODO("Not yet implemented")
    }
}