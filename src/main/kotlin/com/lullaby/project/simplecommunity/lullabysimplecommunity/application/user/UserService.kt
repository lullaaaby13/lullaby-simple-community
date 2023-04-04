package com.lullaby.project.simplecommunity.lullabysimplecommunity.application.user

import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.ConflictException
import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.NotFoundException
import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.UnauthorizedException
import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.user.User
import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(
    val userRepository: UserRepository,
) {
    fun exist(userId: Long): Boolean {
        return userRepository.findById(userId) != null
    }

    fun getUser(userId: Long): UserDto {
        val user = userRepository.findById(userId)
            ?: throw NotFoundException("존재 하지 않는 사용자 입니다. [userId = $userId]")
        return UserDto(user)
    }

    fun createUser(command: SignInCommand): UserDto {
        val findUser = userRepository.findByAccount(command.account)
        if (findUser != null) {
            throw ConflictException("이미 존재하는 사용자 입니다. [account = ${command.account}]")
        }

        val user = User(
            account = command.account,
            password = command.password,
            createdAt = LocalDateTime.now(),
            lastLoggedInAt = null,
        )

        val savedUser = userRepository.save(user)
        return UserDto(savedUser)
    }



}