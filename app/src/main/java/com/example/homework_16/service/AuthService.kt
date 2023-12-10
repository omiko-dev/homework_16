package com.example.homework_16.service

import com.example.homework_16.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun login(@Body user: User) : Response<Any>

    @Headers("Content-Type: application/json")
    @POST("register")
    suspend fun register(@Body user: User) : Response<Any>
}