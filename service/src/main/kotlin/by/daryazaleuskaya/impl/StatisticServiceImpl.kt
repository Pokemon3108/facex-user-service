package by.daryazaleuskaya.impl

import by.daryazaleuskaya.PersonService
import by.daryazaleuskaya.StatisticService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StatisticServiceImpl @Autowired constructor(
    private val personService: PersonService
) : StatisticService {

    override fun addRecord(username: String) {




    }
}