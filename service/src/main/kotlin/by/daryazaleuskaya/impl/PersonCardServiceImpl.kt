package by.daryazaleuskaya.impl

import by.daryazaleuskaya.PersonCardService
import by.daryazaleuskaya.dto.PersonCardDto
import by.daryazaleuskaya.repos.PersonCardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonCardServiceImpl @Autowired constructor(
    private val personCardRepository: PersonCardRepository
) : PersonCardService {

    override fun save(personCardDto: PersonCardDto) {
        val personCardDataModel = personCardDto.toPersonCardDataModel()
        personCardRepository.save(personCardDataModel)
    }
}