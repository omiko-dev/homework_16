package com.example.homework_16.dto

import com.squareup.moshi.Json

data class AuthDto(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String
)

