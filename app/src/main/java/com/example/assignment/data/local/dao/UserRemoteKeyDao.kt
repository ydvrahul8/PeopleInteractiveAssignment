package com.example.assignment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignment.data.local.entity.UserRemoteKeyEntity

@Dao
interface UserRemoteKeyDao {

    @Query("SELECT * FROM UserRemoteKey WHERE id =:id")
    suspend fun getRemoteKeys(id: String): UserRemoteKeyEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<UserRemoteKeyEntity>)

    @Query("DELETE FROM UserRemoteKey")
    suspend fun deleteAllRemoteKeys()
}