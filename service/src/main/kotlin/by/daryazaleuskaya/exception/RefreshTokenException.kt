package by.daryazaleuskaya.exception

class RefreshTokenException(val token: String, message: String)
    : RuntimeException(message)
