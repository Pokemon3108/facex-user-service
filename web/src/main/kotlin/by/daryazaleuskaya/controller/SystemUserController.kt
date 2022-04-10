package by.daryazaleuskaya.controller

import by.daryazaleuskaya.SystemUserService
import by.daryazaleuskaya.dto.SystemUserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/user")
class SystemUserController @Autowired constructor(
    private val systemUserService : SystemUserService
){

    @GetMapping("/{id}")
    fun readSystemUser(@PathVariable id: String) : SystemUserDto {
        return systemUserService.read(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createSystemUser(@RequestBody user : SystemUserDto) : SystemUserDto {

        return systemUserService.create(user)
    }
}