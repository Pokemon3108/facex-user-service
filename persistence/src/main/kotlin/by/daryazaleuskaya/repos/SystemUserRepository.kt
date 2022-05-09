package by.daryazaleuskaya.repos

import by.daryazaleuskaya.datamodel.SystemUserDataModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SystemUserRepository : MongoRepository<SystemUserDataModel, String> {

    fun findByLogin(login : String) : SystemUserDataModel?
}