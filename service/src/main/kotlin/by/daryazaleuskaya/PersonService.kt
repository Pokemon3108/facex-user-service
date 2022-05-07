package by.daryazaleuskaya

import by.daryazaleuskaya.dto.PersonDto

interface PersonService {

    fun save(personDto: PersonDto) : PersonDto

    fun save(username: String, group : String) : PersonDto

    fun exists(username : String, group : String) : Boolean

}