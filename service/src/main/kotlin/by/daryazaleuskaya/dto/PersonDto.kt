package by.daryazaleuskaya.dto

import org.springframework.data.annotation.Id

data class PersonDto(

    @Id
    var id: String? = null,

    val name: String,
    val surname: String,
    val group : String?
)