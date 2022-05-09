package by.daryazaleuskaya.datamodel

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
class RefreshTokenDataModel(
    @Id
    val id: String?,
    val user: SystemUserDataModel,
    val token: String,
    val expireDate: Date
)