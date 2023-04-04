package com.lullaby.project.simplecommunity.lullabysimplecommunity.application.user

data class SignInCommand(
    val account: String,
    val password: String,
)