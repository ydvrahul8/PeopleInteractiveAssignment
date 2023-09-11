package com.example.assignment.di

import android.content.Context
import androidx.room.Room
import com.example.assignment.data.local.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, "quoteDB").build()
    }
}