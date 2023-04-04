package com.lullaby.project.simplecommunity.lullabysimplecommunity.application.user

data class LoginDto(
    val accessToken: String,
    val refreshToken: String,
)