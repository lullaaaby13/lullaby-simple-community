package com.lullaby.project.simplecommunity.lullabysimplecommunity.application.authenticate

import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.UnauthorizedException
import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.user.LoginCommand
import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.user.LoginDto
import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.user.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthenticateService(
    val jwtProvider: JwtProvider,
    val userRepository: UserRepository,
) {

    fun login(command: LoginCommand): LoginDto {
        val findUser = userRepository.findByAccount(command.account)
        if (findUser == null) {
            println("로그인 실패 > 존재 하지 않는 사용자 [account = ${command.account}]")
            throw UnauthorizedException("인증 정보가 올바르지 않습니다.")
        }

        if (findUser.password != command.password) {
            throw UnauthorizedException("인증 정보가 올바르지 않습니다.")
        }

        val accessToken = jwtProvider.accessToken(findUser.id!!, "USER")
        val refreshToken = jwtProvider.refreshToken(findUser.id, "USER")

        return LoginDto(accessToken, refreshToken)
    }
}