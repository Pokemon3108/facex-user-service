package by.daryazaleuskaya.impl

import by.daryazaleuskaya.SystemUserService
import by.daryazaleuskaya.dto.SystemUserDto
import by.daryazaleuskaya.exception.NotFoundResourceException
import by.daryazaleuskaya.repos.SystemUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SystemUserServiceImpl @Autowired constructor(
    private val userRepository: SystemUserRepository
) : SystemUserService {

    override fun create(user: SystemUserDto): SystemUserDto {

        val userModel = user.toSystemUserDataModel()
        return userRepository.save(userModel).toSystemUserDto()
    }

    override fun read(id: String): SystemUserDto {

        val userDataModel =  userRepository.findById(id)
        if (userDataModel.isPresent) {
            return userDataModel.get().toSystemUserDto()
        } else {
            throw NotFoundResourceException()
        }
    }
}