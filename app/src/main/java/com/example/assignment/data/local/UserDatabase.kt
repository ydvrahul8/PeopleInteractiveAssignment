package com.example.assignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assignment.data.local.dao.UserDao
import com.example.assignment.data.local.dao.UserRemoteKeyDao
import com.example.assignment.data.local.entity.UserEntity
import com.example.assignment.data.local.entity.UserRemoteKeyEntity

@Database(
    entities = [UserEntity::class, UserRemoteKeyEntity::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun userRemoteKeyDao(): UserRemoteKeyDao

}