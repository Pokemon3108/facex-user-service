package by.daryazaleuskaya.impl

import by.daryazaleuskaya.PersonService
import by.daryazaleuskaya.datamodel.PersonDataModel
import by.daryazaleuskaya.dto.PersonDto
import by.daryazaleuskaya.repos.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl @Autowired constructor(
    private val personRepository: PersonRepository
) : PersonService {

    override fun save(personDto: PersonDto): PersonDto {
        val personDataModel = personDto.toPersonDataModel()
        return personRepository.save(personDataModel).toPersonDto()
    }

    override fun save(username: String, group : String) : PersonDto {
        val personDataModel = buildPersonDataModel(username, group)
        return personRepository.save(personDataModel).toPersonDto()
    }

    override fun exists(username : String, group : String) : Boolean {
        val personDataModel = buildPersonDataFromUserName(username)
        return personRepository.exists(Example.of(personDataModel))
    }

    override fun buildPersonDto(username: String, group: String): PersonDto {
        return buildPersonDataModel(username, group).toPersonDto()
    }

    private fun buildPersonDataModel(username: String, group: String) : PersonDataModel {
        val personDataModel = buildPersonDataFromUserName(username)
        personDataModel.group = group
        return personDataModel
    }

    private fun buildPersonDataFromUserName(username : String) : PersonDataModel {
        val parsedName = username.split("_")
        return PersonDataModel(name = parsedName[0], surname = parsedName[1])
    }
}