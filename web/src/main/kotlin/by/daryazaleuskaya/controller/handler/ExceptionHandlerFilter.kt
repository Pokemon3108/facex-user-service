package by.daryazaleuskaya.controller.handler

import by.daryazaleuskaya.exception.RefreshTokenException
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.servlet.HandlerExceptionResolver
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ExceptionHandlerFilter @Autowired constructor(@Qualifier("handlerExceptionResolver") private val resolver: HandlerExceptionResolver) :
    OncePerRequestFilter() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: ExpiredJwtException) {
            resolver.resolveException(request, response, null, e)
        } catch (e: RefreshTokenException) {
            resolver.resolveException(request, response, null, e)
        }
    }
}
