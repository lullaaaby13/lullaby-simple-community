package com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.user

import java.time.LocalDateTime

data class User(
    val id: Long? = null,
    val account: String,
    val password: String,
    val createdAt: LocalDateTime,
    val lastLoggedInAt: LocalDateTime?,
)