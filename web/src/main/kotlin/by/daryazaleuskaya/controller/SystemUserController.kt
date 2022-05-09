package by.daryazaleuskaya.controller

import by.daryazaleuskaya.SystemUserService
import by.daryazaleuskaya.dto.SystemUserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/user")
class SystemUserController @Autowired constructor(
    private val systemUserService : SystemUserService
){

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createSystemUser(@RequestBody user : SystemUserDto) : SystemUserDto {

        return systemUserService.create(user)
    }
}