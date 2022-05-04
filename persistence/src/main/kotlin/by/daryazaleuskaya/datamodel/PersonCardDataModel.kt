package by.daryazaleuskaya.datamodel

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
data class PersonCardDataModel (

    @Id
    val id: String?,

    val name: String,
    val surname: String,
    val gender: String,
    val birthdate: LocalDate,
    val country: String,
    val city: String
)