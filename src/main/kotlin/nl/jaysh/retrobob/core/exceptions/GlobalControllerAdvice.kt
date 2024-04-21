package nl.jaysh.retrobob.core.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerAdvice {
    @ExceptionHandler(InvalidRequestException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun invalidRequestExceptionHandler(e: InvalidRequestException): InvalidRequestExceptionResponse {
        val subject = e.subject
        val violation = e.violation
        val errors = mapOf(subject to listOf(violation))
        return InvalidRequestExceptionResponse(errors)
    }
}

data class InvalidRequestExceptionResponse(val errors: Map<String, List<String>>)
