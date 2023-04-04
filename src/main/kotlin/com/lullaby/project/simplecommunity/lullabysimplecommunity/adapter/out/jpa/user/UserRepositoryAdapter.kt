package com.lullaby.project.simplecommunity.lullabysimplecommunity.adapter.out.jpa.user

import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.user.User
import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class UserRepositoryAdapter(
    val userJpaRepository: UserJpaRepository,
): UserRepository {
    override fun findByAccount(account: String): User? {
        return userJpaRepository.findByAccount(account)?.toDomain()
    }

    override fun save(user: User): User {
        val userEntity = UserEntity(user.id,
            user.account,
            user.password,
            user.createdAt,
            user.lastLoggedInAt)
        val savedUser = userJpaRepository.save(userEntity)
        return savedUser.toDomain()
    }

    override fun findById(userId: Long): User? {
        return userJpaRepository.findByIdOrNull(userId)?.toDomain()
    }
}