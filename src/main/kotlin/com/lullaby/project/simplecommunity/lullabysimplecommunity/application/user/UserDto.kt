package com.lullaby.project.simplecommunity.lullabysimplecommunity.application.user

import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.user.User
import java.time.LocalDateTime

data class UserDto(
    val id: Long,
    val account: String,
    val createdAt: LocalDateTime,
    val lastLoggedInAt: LocalDateTime?,
) {
    constructor(user: User): this(
        user.id!!,
        user.account,
        user.createdAt,
        user.lastLoggedInAt,
    )
}