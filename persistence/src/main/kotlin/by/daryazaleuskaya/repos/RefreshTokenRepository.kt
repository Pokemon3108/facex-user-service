package by.daryazaleuskaya.repos

import by.daryazaleuskaya.datamodel.RefreshTokenDataModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : MongoRepository<RefreshTokenDataModel, String> {

    fun findByToken(token : String) : RefreshTokenDataModel?

    fun findByUserLogin(login : String) : RefreshTokenDataModel?
}