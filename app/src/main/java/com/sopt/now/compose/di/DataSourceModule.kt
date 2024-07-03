package com.sopt.now.compose.di

import com.sopt.now.compose.data.datasource.FriendRemoteDataSource
import com.sopt.now.compose.data.datasourceImpl.FriendRemoteDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindFriendsList(friendDatasourceImpl: FriendRemoteDatasourceImpl): FriendRemoteDataSource
}