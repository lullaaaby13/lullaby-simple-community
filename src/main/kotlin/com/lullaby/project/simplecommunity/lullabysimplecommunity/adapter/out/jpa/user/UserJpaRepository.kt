package com.lullaby.project.simplecommunity.lullabysimplecommunity.adapter.out.jpa.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository: JpaRepository<UserEntity, Long> {
    fun findByAccount(account: String): UserEntity?
}