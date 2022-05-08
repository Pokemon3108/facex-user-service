package by.daryazaleuskaya.impl

import by.daryazaleuskaya.StatisticService
import by.daryazaleuskaya.dto.PersonDto
import by.daryazaleuskaya.dto.PersonStatisticDto
import by.daryazaleuskaya.repos.PersonStatisticRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class StatisticServiceImpl @Autowired constructor(
    private val personStatisticRepository: PersonStatisticRepository
) : StatisticService {

    override fun addRecord(personDto: PersonDto) {
        val currentTime = LocalDateTime.now()
        val personStatisticDto = PersonStatisticDto(personDto = personDto, attendanceTime = currentTime)
        personStatisticRepository.save(personStatisticDto.toPersonStatisticDataModel())
    }

}