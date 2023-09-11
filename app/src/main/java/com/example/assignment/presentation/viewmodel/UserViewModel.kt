package com.example.assignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.assignment.domain.model.User
import com.example.assignment.domain.usecase.GetUserUseCase
import com.example.assignment.domain.usecase.UpdateUserUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUserCase
) : ViewModel() {
    val list = userUseCase().cachedIn(viewModelScope)

    fun updateUser(user: User) = viewModelScope.launch {
        updateUserUseCase.invoke(user)
    }
}