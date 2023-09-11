package com.example.assignment.domain.model


data class User(
    val cell: String?,
    val email: String?,
    val gender: String?,
    val id: String?,
    val address: String?,
    val name: String,
    val phone: String?,
    val image: String?,
    var status:String="none"
)