package by.daryazaleuskaya.impl

import by.daryazaleuskaya.datamodel.PersonDataModel
import by.daryazaleuskaya.datamodel.PersonStatisticDataModel
import by.daryazaleuskaya.datamodel.RefreshTokenDataModel
import by.daryazaleuskaya.datamodel.SystemUserDataModel
import by.daryazaleuskaya.dto.PersonDto
import by.daryazaleuskaya.dto.PersonStatisticDto
import by.daryazaleuskaya.dto.RefreshTokenDto
import by.daryazaleuskaya.dto.SystemUserDto

fun SystemUserDto.toSystemUserDataModel() = SystemUserDataModel(
    id = id,
    login = login,
    password = password,
    name = name,
    surname = surname,
    email = email,
    company = company,
    authorities = authorities
)

fun SystemUserDataModel.toSystemUserDto() = SystemUserDto(
    id = id,
    login = login,
    password = password,
    name = name,
    surname = surname,
    email = email,
    company = company,
    authorities = authorities
)

fun PersonDto.toPersonDataModel() = PersonDataModel(
    id = id,
    name = name,
    surname = surname,
    group = group
)

fun PersonDataModel.toPersonDto() = PersonDto(
    id = id,
    name = name,
    surname = surname,
    group = group
)

fun PersonStatisticDataModel.toPersonStatisticDto() = PersonStatisticDto(
    id = id,
    personDto = personDataModel.toPersonDto(),
    attendanceTime = attendanceTime
)

fun PersonStatisticDto.toPersonStatisticDataModel() = PersonStatisticDataModel(
    id = id,
    personDataModel = personDto.toPersonDataModel(),
    attendanceTime = attendanceTime
)

fun RefreshTokenDataModel.toRefreshTokenDto() = RefreshTokenDto(
    id = id,
    user = user.toSystemUserDto(),
    token = token,
    expireDate = expireDate
)

fun RefreshTokenDto.toRefreshTokenDataModel() = RefreshTokenDataModel(
    id = id,
    user = user.toSystemUserDataModel(),
    token = token,
    expireDate = expireDate
)