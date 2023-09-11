package com.example.assignment.data.remote.dto


import com.example.assignment.data.remote.dto.Info
import com.example.assignment.data.remote.dto.Result
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserListDTO(
    @Json(name = "info")
    val info: Info?,
    @Json(name = "results")
    val results: List<Result>?
)