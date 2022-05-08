package by.daryazaleuskaya.controller

import by.daryazaleuskaya.StatisticService
import by.daryazaleuskaya.dto.PersonStatisticDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/statistic")
class StatisticController @Autowired constructor(
    private val statisticService: StatisticService
) {

    @GetMapping
    fun findAll(@RequestParam("surname", required = false, defaultValue = "") surname : String) : List<PersonStatisticDto> {

        return statisticService.readAllBySurname(surname)
    }

}