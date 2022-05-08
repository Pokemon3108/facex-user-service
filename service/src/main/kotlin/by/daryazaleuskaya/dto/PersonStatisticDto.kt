package by.daryazaleuskaya.dto

import java.time.LocalDateTime

data class PersonStatisticDto(
    var id: String? = null,
    val personDto: PersonDto,
    val attendanceTime: LocalDateTime
)