package by.daryazaleuskaya.dto

import org.springframework.data.annotation.Id
import java.time.LocalDate

data class PersonCardDto(

    @Id
    val id: String?,

    val name: String,
    val surname: String,
    val gender: String,
    val birthdate: LocalDate,
    val country: String,
    val city: String
)