package by.daryazaleuskaya

import by.daryazaleuskaya.dto.PersonDto
import by.daryazaleuskaya.dto.PersonStatisticDto

interface StatisticService {

    fun addRecord(personDto: PersonDto)

    fun readAllBySurname(surname : String) : List<PersonStatisticDto>

}