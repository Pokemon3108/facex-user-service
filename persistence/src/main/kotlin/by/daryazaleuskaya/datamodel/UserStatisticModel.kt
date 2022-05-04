package by.daryazaleuskaya.datamodel

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class UserStatisticModel(

    @Id
    val id: Long,

    val userDataModel : SystemUserDataModel,
    val attendanceTime: LocalDateTime,
    val attendanceAmount: Int
)
