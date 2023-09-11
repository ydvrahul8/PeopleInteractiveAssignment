package com.example.assignment.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val cell: String?,
    val email: String?,
    val gender: String?,
    val address: String?,
    val name: String,
    val phone: String?,
    val image: String?,
    val status: String = "none"
)