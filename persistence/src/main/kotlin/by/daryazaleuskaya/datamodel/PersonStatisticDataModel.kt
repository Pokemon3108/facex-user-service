package by.daryazaleuskaya.datamodel

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class PersonStatisticDataModel(

    @Id
    var id: String? = null,

    val personDataModel : PersonDataModel,
    val attendanceTime: LocalDateTime
)
