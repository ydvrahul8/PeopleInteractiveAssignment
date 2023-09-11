package com.example.assignment.data.remote

import com.example.assignment.data.remote.dto.UserListDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface UserAPI {
    @GET("api/?results=20")
    suspend fun getUsers(@Query("page") page: Int): UserListDTO
}