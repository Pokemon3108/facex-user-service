package by.daryazaleuskaya.repos

import by.daryazaleuskaya.datamodel.PersonCardDataModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonCardRepository : MongoRepository<PersonCardDataModel, String> {
}