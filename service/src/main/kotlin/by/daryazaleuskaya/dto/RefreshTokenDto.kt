package by.daryazaleuskaya.dto

import java.util.*

class RefreshTokenDto(
    val id: String?,
    val user: SystemUserDto,
    val token: String,
    val expireDate: Date
)