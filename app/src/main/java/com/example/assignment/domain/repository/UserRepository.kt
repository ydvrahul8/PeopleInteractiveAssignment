package com.example.assignment.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.assignment.domain.model.User

interface UserRepository {
    fun getUser(): LiveData<PagingData<User>>

    suspend fun updateUser(user: User)
}