package by.daryazaleuskaya.impl

import by.daryazaleuskaya.DtoConverterService
import by.daryazaleuskaya.datamodel.UserAccountModel
import by.daryazaleuskaya.dto.UserDto
import org.springframework.stereotype.Service

@Service
class UserDtoConverterServiceImpl : DtoConverterService<UserAccountModel, UserDto> {

    override fun dataModelToDto(model: UserAccountModel): UserDto {
        return UserDto(
            login = model.login,
            password = model.password,
            id = model.id,
            name = model.userDetails?.name,
            surname = model.userDetails?.surname,
            birthDate = model.userDetails?.birthDate,
            country = model.userDetails?.country
        )
    }

    override fun dtoToDataModel(dto : UserDto) : UserAccountModel {
        val dataModel = UserAccountModel(
            login = dto.login,
            password = dto.password,
            id = dto.id
        )
        val userDetails = UserAccountModel.UserDetailsModel(
            name = dto.name,
            surname = dto.surname,
            birthDate = dto.birthDate,
            country = dto.country
        )
        dataModel.userDetails = userDetails
        return dataModel
    }

}