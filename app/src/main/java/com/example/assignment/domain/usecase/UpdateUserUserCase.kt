package com.example.assignment.domain.usecase

import com.example.assignment.domain.model.User
import com.example.assignment.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserUserCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(user: User) = repository.updateUser(user)
}