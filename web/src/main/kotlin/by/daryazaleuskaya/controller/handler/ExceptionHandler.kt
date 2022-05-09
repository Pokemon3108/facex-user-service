package by.daryazaleuskaya.controller.handler

import by.daryazaleuskaya.exception.NoSystemUserException
import by.daryazaleuskaya.exception.RecognitionException
import by.daryazaleuskaya.exception.RefreshTokenException
import by.daryazaleuskaya.message.MessageService
import by.daryazaleuskaya.model.ErrorData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionHandler @Autowired constructor(
    private val messageService : MessageService
) : ResponseEntityExceptionHandler() {

    private val SYSTEM_USER_NOT_FOUND  = "systemUser.not.found"

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNoSystemUserException(noSystemUserException: NoSystemUserException) : ErrorData {

        val message = messageService.getMessage(SYSTEM_USER_NOT_FOUND, noSystemUserException.username)
        return ErrorData(message)
    }

    @ExceptionHandler
    fun handleRecognitionException(recognitionException: RecognitionException) : ResponseEntity<ErrorData> {

        val body = ErrorData(recognitionException.message)
        return ResponseEntity<ErrorData>(body, HttpStatus.valueOf(recognitionException.statusCode))
    }

    @ExceptionHandler
    fun handleRefreshTokenException(refreshTokenException: RefreshTokenException) : ErrorData {

        val message = messageService.getMessage(refreshTokenException.message!!, refreshTokenException.token)
        return ErrorData(message)
    }

}