package by.daryazaleuskaya

import by.daryazaleuskaya.dto.PersonDto

interface PersonService {

    fun save(personDto: PersonDto) : PersonDto

    fun save(username: String) : PersonDto

    fun exists(username : String) : Boolean

}