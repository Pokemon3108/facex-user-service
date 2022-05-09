package by.daryazaleuskaya.security

class JwtResponse(
    private val username: String,
    private val accessToken: String,
    private val refreshToken: String,
    private val expires: Long
)