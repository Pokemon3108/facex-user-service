package by.daryazaleuskaya.repos

import by.daryazaleuskaya.datamodel.PersonDataModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : MongoRepository<PersonDataModel, String> {
}