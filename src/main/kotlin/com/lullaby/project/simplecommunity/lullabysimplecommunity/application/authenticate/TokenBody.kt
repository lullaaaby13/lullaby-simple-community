package com.lullaby.project.simplecommunity.lullabysimplecommunity.application.authenticate

data class TokenBody(
    val userId: Long,
    val role: String,
)