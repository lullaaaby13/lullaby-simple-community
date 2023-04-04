package com.lullaby.project.simplecommunity.lullabysimplecommunity.adapter.`in`.web

import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.NotFoundException
import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.UnauthorizedException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class ExceptionHandler {

    fun notFoundException() {

    }

    @ExceptionHandler(UnauthorizedException::class)
    fun unauthorizedException(request: HttpServletRequest, exception: UnauthorizedException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.UNAUTHORIZED.value(),
            error = exception.message ?: "Unauthorized",
            path = request.requestURI,
        )
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED.value())
            .body(errorResponse)
    }

    @ExceptionHandler(NotFoundException::class)
    fun notFoundExceptionHandler(request: HttpServletRequest, exception: NotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.NOT_FOUND.value(),
            error = exception.message ?: "Not Found",
            path = request.requestURI,
        )
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND.value())
            .body(errorResponse)
    }

}

data class ErrorResponse(
    val timestamp: LocalDateTime,
    val status: Int,
    val error: String,
    val path: String,
)