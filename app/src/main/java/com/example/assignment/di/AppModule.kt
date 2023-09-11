package com.example.assignment.di

import com.example.assignment.Constants
import com.example.assignment.data.local.UserDatabase
import com.example.assignment.data.remote.UserAPI
import com.example.assignment.data.repository.UserRepositoryImpl
import com.example.assignment.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideUserAPI(client: OkHttpClient): UserAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(UserAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .build()
    }

    @Singleton
    @Provides
    fun provideRepository(userAPI: UserAPI, userDatabase: UserDatabase): UserRepository {
        return UserRepositoryImpl(userAPI, userDatabase)
    }
    /*   @Provides
       @Singleton
       fun provideUserRepository(api: UserAPI): UserRepository {
           return UserRepositoryImpl(api)
       }*/

    /*    @Provides
        @Singleton
        fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
            return Room
                .databaseBuilder(
                    context,
                    UserDatabase::class.java, "users.db"
                ).fallbackToDestructiveMigration()
                .build()
        }*/
}