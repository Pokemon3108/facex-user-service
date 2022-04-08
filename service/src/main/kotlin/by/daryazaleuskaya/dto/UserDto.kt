package by.daryazaleuskaya.dto

import lombok.Builder
import java.time.LocalDate

@Builder
class UserDto(

    val id: String?,
    val login: String,
    val password: String,

    val name: String?,
    val surname: String?,
    val birthDate: LocalDate?,
    val country: String?
)