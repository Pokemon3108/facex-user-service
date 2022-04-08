package by.daryazaleuskaya.dto

import lombok.Builder

@Builder
class SystemUserDto(

    val id: String?,
    val login: String,
    val password: String,

    val name: String,
    val surname: String,
    val email: String,
    val companyName: String
)