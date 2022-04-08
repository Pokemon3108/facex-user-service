package by.daryazaleuskaya.controller

import by.daryazaleuskaya.UserService
import by.daryazaleuskaya.dto.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController @Autowired constructor(
    private val userService : UserService
){

    @GetMapping("/{id}")
    fun readUser(@PathVariable id: String) : UserDto? {
        return null
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user : UserDto) {

    }
}