package by.daryazaleuskaya.impl

import by.daryazaleuskaya.datamodel.PersonDataModel
import by.daryazaleuskaya.datamodel.SystemUserDataModel
import by.daryazaleuskaya.dto.PersonDto
import by.daryazaleuskaya.dto.SystemUserDto

fun SystemUserDto.toSystemUserDataModel() = SystemUserDataModel(
    id = id,
    login = login,
    password = password,
    name = name,
    surname = surname,
    email = email,
    company = company
)

fun SystemUserDataModel.toSystemUserDto() = SystemUserDto(
    id = id,
    login = login,
    password = password,
    name = name,
    surname = surname,
    email = email,
    company = company
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