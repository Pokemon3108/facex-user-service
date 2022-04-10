package by.daryazaleuskaya

import by.daryazaleuskaya.dto.SystemUserDto
import org.springframework.stereotype.Service

@Service
interface SystemUserService {

    fun create(user : SystemUserDto) : SystemUserDto

    fun read(id : String) : SystemUserDto
}