package by.daryazaleuskaya

import by.daryazaleuskaya.dto.UserDto
import org.springframework.stereotype.Service

@Service
interface UserService {

    fun createUser(user : UserDto) : UserDto
}