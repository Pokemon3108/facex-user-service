package by.daryazaleuskaya.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerExceptionResolver
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class RestAuthenticationEntryPoint @Autowired constructor(
    @Qualifier("handlerExceptionResolver") private val exceptionResolver : HandlerExceptionResolver
) : AuthenticationEntryPoint {

    override fun commence(request: HttpServletRequest, response: HttpServletResponse, exception: AuthenticationException) {

        exceptionResolver.resolveException(request, response, null, exception)
    }
}