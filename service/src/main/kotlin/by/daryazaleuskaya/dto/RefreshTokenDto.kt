package by.daryazaleuskaya.dto

import java.util.*

class RefreshTokenDto(
    var id: String? = null,
    val user: SystemUserDto,
    val token: String,
    val expireDate: Date
)