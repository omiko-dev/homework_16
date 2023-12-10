package com.example.homework_16.model

import com.squareup.moshi.Json

data class User(
    @Json(name = "id") val id: Int?,
    @Json(name = "token") val token: String
)
