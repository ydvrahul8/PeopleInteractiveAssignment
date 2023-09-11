package com.example.assignment.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Name(
    @Json(name = "first")
    val first: String?,
    @Json(name = "last")
    val last: String?,
    @Json(name = "title")
    val title: String?
)