package by.daryazaleuskaya.exception

class RecognitionException(
    override val message : String,
    val statusCode : Int
) : Exception(message)