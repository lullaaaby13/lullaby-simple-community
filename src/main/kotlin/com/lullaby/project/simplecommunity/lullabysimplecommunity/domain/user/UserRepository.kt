package com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.user

interface UserRepository {
    fun findByAccount(account: String): User?
    fun save(user: User): User
    fun findById(userId: Long): User?
}