package by.daryazaleuskaya.repos

import by.daryazaleuskaya.datamodel.SystemUserDataModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SystemUserAccountRepository : MongoRepository<SystemUserDataModel, String> {


}