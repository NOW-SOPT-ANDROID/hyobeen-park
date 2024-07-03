package com.sopt.now.compose.di

import com.sopt.now.compose.data.service.FriendService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SeviceModule {
    @Provides
    @Singleton
    fun provideFriendService(retrofit: Retrofit): FriendService =
        retrofit.create(FriendService::class.java)
}