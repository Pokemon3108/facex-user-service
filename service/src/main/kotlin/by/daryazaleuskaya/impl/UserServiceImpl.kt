package by.daryazaleuskaya.impl

import by.daryazaleuskaya.DtoConverterService
import by.daryazaleuskaya.UserService
import by.daryazaleuskaya.datamodel.UserAccountModel
import by.daryazaleuskaya.dto.UserDto
import by.daryazaleuskaya.repos.UserAccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl @Autowired constructor(
    private val dtoConverterService : DtoConverterService<UserAccountModel, UserDto>,
    private val userAccountRepository: UserAccountRepository
) : UserService {

    override fun createUser(user: UserDto): UserDto {

        val userModel = dtoConverterService.dtoToDataModel(user);
        return dtoConverterService.dataModelToDto(userAccountRepository.save(userModel));
    }
}