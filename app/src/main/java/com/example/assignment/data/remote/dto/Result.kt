package com.example.assignment.data.remote.dto


import com.example.assignment.data.remote.dto.Dob
import com.example.assignment.data.remote.dto.Id
import com.example.assignment.data.remote.dto.Location
import com.example.assignment.data.remote.dto.Login
import com.example.assignment.data.remote.dto.Name
import com.example.assignment.data.remote.dto.Picture
import com.example.assignment.data.remote.dto.Registered
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "cell")
    val cell: String?,
    @Json(name = "dob")
    val dob: Dob?,
    @Json(name = "email")
    val email: String?,
    @Json(name = "gender")
    val gender: String?,
    @Json(name = "id")
    val id: Id?,
    @Json(name = "location")
    val location: Location?,
    @Json(name = "login")
    val login: Login?,
    @Json(name = "name")
    val name: Name?,
    @Json(name = "nat")
    val nat: String?,
    @Json(name = "phone")
    val phone: String?,
    @Json(name = "picture")
    val picture: Picture?,
    @Json(name = "registered")
    val registered: Registered?
)