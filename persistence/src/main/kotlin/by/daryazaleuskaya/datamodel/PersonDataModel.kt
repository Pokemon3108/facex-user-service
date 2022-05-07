package by.daryazaleuskaya.datamodel

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class PersonDataModel(

    @Id
    var id: String? = null,

    val name: String,
    val surname: String,
    var group : String? = null
)