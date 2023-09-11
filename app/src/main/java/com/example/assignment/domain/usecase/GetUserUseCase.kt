package com.example.assignment.domain.usecase

import com.example.assignment.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: UserRepository) {
    operator fun invoke() = repository.getUser()
}