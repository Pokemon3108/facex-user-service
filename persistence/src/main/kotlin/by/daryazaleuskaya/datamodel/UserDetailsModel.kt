package by.daryazaleuskaya.datamodel

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
class UserDetailsModel(

    @Id
    val id : Long,
    val name: String,
    val surname: String,
    val birthDate : LocalDate
) { }