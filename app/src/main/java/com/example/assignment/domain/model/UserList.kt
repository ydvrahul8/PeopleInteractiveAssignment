package com.example.assignment.domain.model

import com.example.assignment.domain.model.Info
import com.example.assignment.domain.model.User

data class UserList (
    val info: Info?,
    val results: List<User>?
)