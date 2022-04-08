package by.daryazaleuskaya.impl

import by.daryazaleuskaya.SystemUserService
import by.daryazaleuskaya.dto.SystemUserDto
import by.daryazaleuskaya.repos.SystemUserAccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SystemUserServiceImpl @Autowired constructor(
    private val userAccountRepository: SystemUserAccountRepository
) : SystemUserService {

    override fun createUser(user: SystemUserDto): SystemUserDto {

        val userModel = user.toSystemUserDataModel()
        return userAccountRepository.save(userModel).toSystemUserDto()
    }
}