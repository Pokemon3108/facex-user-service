package by.daryazaleuskaya.datamodel

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
data class UserAccountModel(

    @Id
    val id: String?,
    val login: String,
    val password: String

) {

    var userDetails: UserDetailsModel? = null

    class UserDetailsModel(

        val name: String?,
        val surname: String?,
        val birthDate: LocalDate?,
        val country : String?
    )
}