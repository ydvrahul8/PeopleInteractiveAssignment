package com.example.assignment.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import androidx.paging.map
import com.example.assignment.data.UserRemoteMediator
import com.example.assignment.data.local.UserDatabase
import com.example.assignment.data.remote.UserAPI
import com.example.assignment.domain.model.User
import com.example.assignment.domain.repository.UserRepository
import com.example.assignment.mapper.toUser
import com.example.assignment.mapper.toUserEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserRepositoryImpl @Inject constructor(
    private val userAPI: UserAPI,
    private val userDatabase: UserDatabase
) : UserRepository {

    override fun getUser(): LiveData<PagingData<User>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 100
        ),
        remoteMediator = UserRemoteMediator(userAPI, userDatabase),
        pagingSourceFactory = { userDatabase.userDao().getUser() }
    ).liveData.map { data -> data.map { it.toUser() } }

    override suspend fun updateUser(user: User) {
        userDatabase.userDao().updateUser(user.toUserEntity())
    }

}