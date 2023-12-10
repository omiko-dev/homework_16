package com.example.homework_16.service

import com.example.homework_16.dto.AuthDto
import com.example.homework_16.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun login(@Body authDto: AuthDto) : Response<User>

    @Headers("Content-Type: application/json")
    @POST("register")
    suspend fun register(@Body authDto: AuthDto) : Response<User>
}


enum class AuthErrorMessage(val msg: String){
    INCORRECT_EMAIL("Enter Correct Email"),
    EMPTY_FIELDS("Fill In All Fields"),
    INCORRECT_DATA("Email Or Password Is Incorrect")
}
