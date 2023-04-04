package com.lullaby.project.simplecommunity.lullabysimplecommunity.adapter.`in`.web

import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.authenticate.AuthenticateService
import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.authenticate.JwtProvider
import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.user.LoginCommand
import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.user.SignInCommand
import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("auth")
@RestController
class AuthController(
    val userService: UserService,
    val authenticateService: AuthenticateService,
) {
    @PostMapping("login")
    fun login(@RequestBody request: LoginRequest): LoginResponse {
        val command = LoginCommand(request.account, request.password)
        val loginDto = authenticateService.login(command)
        return LoginResponse(loginDto.accessToken, loginDto.refreshToken)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("sign-in")
    fun signIn(@RequestBody request: SignInRequest) {
        val command = SignInCommand(request.account, request.password)
        val signIn = userService.createUser(command)
    }
}

data class LoginRequest(val account: String, val password: String)
data class LoginResponse(val accessToken: String, val refreshToken: String)
data class SignInRequest(val account: String, val password: String)