package com.example.assignment.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.assignment.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getUser(): PagingSource<Int, UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsers(quotes: List<UserEntity>)

    @Query("DELETE FROM User")
    suspend fun deleteUsers()

    @Update
    suspend fun updateUser(user:UserEntity)
}