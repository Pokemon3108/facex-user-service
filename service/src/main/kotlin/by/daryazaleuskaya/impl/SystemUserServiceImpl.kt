package by.daryazaleuskaya.impl

import by.daryazaleuskaya.SystemUserService
import by.daryazaleuskaya.dto.SystemUserDto
import by.daryazaleuskaya.repos.SystemUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class SystemUserServiceImpl @Autowired constructor(
    private val userRepository: SystemUserRepository
) : SystemUserService, UserDetailsService {

    override fun create(user: SystemUserDto): SystemUserDto {

        val userModel = user.toSystemUserDataModel()
        return userRepository.save(userModel).toSystemUserDto()
    }

    override fun loadUserByUsername(username: String): UserDetails {

        return userRepository.findByLogin(username)?.toSystemUserDto()
            ?: throw UsernameNotFoundException(username)
    }
}