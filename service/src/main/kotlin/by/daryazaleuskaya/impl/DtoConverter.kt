package by.daryazaleuskaya.impl

import by.daryazaleuskaya.datamodel.PersonCardDataModel
import by.daryazaleuskaya.datamodel.SystemUserDataModel
import by.daryazaleuskaya.dto.PersonCardDto
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

fun PersonCardDto.toPersonCardDataModel() = PersonCardDataModel(
    id = id,
    name = name,
    surname = surname,
    gender = gender,
    birthdate = birthdate,
    country = country,
    city = city
)

fun PersonCardDataModel.toPersonCardDto() = PersonCardDto(
    id = id,
    name = name,
    surname = surname,
    gender = gender,
    birthdate = birthdate,
    country = country,
    city = city
)