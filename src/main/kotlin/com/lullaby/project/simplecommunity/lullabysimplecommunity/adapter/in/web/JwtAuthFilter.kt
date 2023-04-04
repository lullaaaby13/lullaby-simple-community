package com.lullaby.project.simplecommunity.lullabysimplecommunity.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.UnauthorizedException
import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.authenticate.JwtProvider
import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.authenticate.TokenBody
import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.user.UserService
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.security.Principal
import java.time.LocalDateTime

@Component
class JwtAuthFilter(
    val jwtProvider: JwtProvider,
    val objectMapper: ObjectMapper
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val authorization = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            unauthorized(request, response)
            return
        }

        try {
            val accessToken = authorization.substringAfter("Bearer ")

            val tokenBody: TokenBody = jwtProvider.parse(accessToken)

            val userPrincipal = User.builder()
                .username(tokenBody.userId.toString())
                .password("")
                .roles(tokenBody.role)
                .build()

            val authentication =
                UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.authorities)
            authentication.also { SecurityContextHolder.getContext().authentication = it }
        } catch (exception: Exception) {
            unauthorized(request, response)
            return
        }
        filterChain.doFilter(request, response)
    }

    fun unauthorized(request: HttpServletRequest, response: HttpServletResponse) {
        val errorResponse = ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.UNAUTHORIZED.value(),
            "Unauthorized",
            request.requestURI
        )
        val jsonString = objectMapper.writeValueAsString(errorResponse)
        response.addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        response.outputStream.write(jsonString.encodeToByteArray())
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val requestURI = request.requestURI
        return requestURI == "/"
                || requestURI.startsWith("/auth/login")
                || requestURI.startsWith("/auth/sign-in")
    }
}