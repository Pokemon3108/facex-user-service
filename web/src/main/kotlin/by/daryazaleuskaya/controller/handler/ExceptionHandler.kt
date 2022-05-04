package by.daryazaleuskaya.controller.handler

import by.daryazaleuskaya.exception.NoSystemUserException
import by.daryazaleuskaya.model.ErrorData
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @Value("\${system-user.not.found}")
    private lateinit var SYSTEM_USER_NOT_FOUND : String

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNoSystemUserException(noSystemUserException: NoSystemUserException) : ErrorData {
        return ErrorData(SYSTEM_USER_NOT_FOUND)
    }

}