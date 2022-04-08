package by.daryazaleuskaya.impl

import by.daryazaleuskaya.datamodel.SystemUserDataModel
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