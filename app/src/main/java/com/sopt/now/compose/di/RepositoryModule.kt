package com.sopt.now.compose.di

import com.sopt.now.compose.data.repository.FriendsRepositoryImpl
import com.sopt.now.compose.domain.repository.FriendRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFriendsList(repositoryImpl: FriendsRepositoryImpl): FriendRepository
}