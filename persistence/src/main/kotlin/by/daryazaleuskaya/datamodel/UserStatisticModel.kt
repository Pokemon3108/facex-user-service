package by.daryazaleuskaya.datamodel

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class UserStatisticModel(

    @Id
    val id: Long,

    val userId : SystemUserDataModel,
    val attendanceTime: LocalDateTime,
    val attendanceAmount: Int
)
