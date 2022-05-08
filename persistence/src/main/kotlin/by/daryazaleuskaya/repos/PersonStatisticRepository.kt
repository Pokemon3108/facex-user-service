package by.daryazaleuskaya.repos

import by.daryazaleuskaya.datamodel.PersonStatisticDataModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface PersonStatisticRepository : MongoRepository<PersonStatisticDataModel, String> {

    @Query("{\"personDataModel.surname\" : {\$regex : ?0}}")
    fun findAllByPersonDataModelSurname(surname: String): List<PersonStatisticDataModel>
}