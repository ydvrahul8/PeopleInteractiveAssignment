package com.example.assignment.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.assignment.data.local.UserDatabase
import com.example.assignment.data.local.entity.UserEntity
import com.example.assignment.data.local.entity.UserRemoteKeyEntity
import com.example.assignment.data.remote.UserAPI
import com.example.assignment.mapper.toUserEntity

@ExperimentalPagingApi
class UserRemoteMediator(
    private val _userAPI: UserAPI,
    private val _userDatabase: UserDatabase
) : RemoteMediator<Int, UserEntity>() {

    private val _userDao = _userDatabase.userDao()
    private val _userRemoteKeysDao = _userDatabase.userRemoteKeyDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, UserEntity>): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = _userAPI.getUsers(currentPage)

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = currentPage + 1

            _userDatabase.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    _userDao.deleteUsers()
                    _userRemoteKeysDao.deleteAllRemoteKeys()
                }
                val users = response.toUserEntity()
                users?.let { _userDao.addUsers(it) }
                val keys = users?.map { user ->
                    UserRemoteKeyEntity(
                        id = user.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                keys?.let { _userRemoteKeysDao.addAllRemoteKeys(it) }
            }
            MediatorResult.Success(false)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, UserEntity>
    ): UserRemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                _userRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UserEntity>
    ): UserRemoteKeyEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { user ->
                user.id.let { _userRemoteKeysDao.getRemoteKeys(id = it) }
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, UserEntity>
    ): UserRemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { user ->
                user.id.let { _userRemoteKeysDao.getRemoteKeys(id = it) }
            }
    }
}