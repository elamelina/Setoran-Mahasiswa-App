package com.example.setoranmahasiswa.model

data class AuthToken(
    val access_token: String,
    val refresh_token: String,
    val expires_in: Int,
    val token_type: String
)