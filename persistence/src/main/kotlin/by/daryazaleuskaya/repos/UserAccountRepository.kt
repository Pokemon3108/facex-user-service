package by.daryazaleuskaya.repos

import by.daryazaleuskaya.datamodel.UserAccountModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserAccountRepository : MongoRepository<UserAccountModel, String> {


}