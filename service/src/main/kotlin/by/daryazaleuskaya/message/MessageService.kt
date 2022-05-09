package by.daryazaleuskaya.message

interface MessageService {

    fun getMessage(messageKey : String, vararg args : Any) : String
}