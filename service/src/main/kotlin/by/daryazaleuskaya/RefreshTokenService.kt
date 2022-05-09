package by.daryazaleuskaya

import by.daryazaleuskaya.dto.RefreshTokenDto
import by.daryazaleuskaya.dto.SystemUserDto

interface RefreshTokenService {

    /**
     * Search token by its name
     *
     * @param token - the name of token
     * @return - found token
     */
    fun findByToken(token: String): RefreshTokenDto

    /**
     * Create refresh token for logged user
     *
     * @param username - the name of user
     * @return created token
     */
    fun createRefreshToken(username: String): RefreshTokenDto

    /**
     * Validates token
     *
     * @param token
     * @param user - the potential owner of token
     */
    fun validateToken(token: RefreshTokenDto, user: SystemUserDto?)

    /**
     * Updates token in storage
     *
     * @param tokenDto - the token to be updated
     * @return - updated token
     */
    fun updateToken(tokenDto: RefreshTokenDto): RefreshTokenDto?
}