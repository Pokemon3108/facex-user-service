package by.daryazaleuskaya.repos

import by.daryazaleuskaya.datamodel.PersonStatisticDataModel
import org.springframework.data.mongodb.repository.MongoRepository

interface PersonStatisticRepository : MongoRepository<PersonStatisticDataModel, String> {
}