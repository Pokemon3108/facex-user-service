package by.daryazaleuskaya.datamodel

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class SystemUserDataModel(

    @Id
    val id: String?,
    val login: String,
    val password: String,

    val name: String,
    val surname: String,
    val email: String,
    val companyName: String

)