package by.daryazaleuskaya.message.impl

import by.daryazaleuskaya.message.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageServiceImpl @Autowired constructor(
    private val messageSource : MessageSource
) : MessageService {


    override fun getMessage(messageKey: String, vararg args: Any): String {

        return messageSource.getMessage(messageKey, args, Locale.US)
    }
}