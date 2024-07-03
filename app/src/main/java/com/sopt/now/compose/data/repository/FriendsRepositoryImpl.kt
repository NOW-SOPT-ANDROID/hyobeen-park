package com.sopt.now.compose.data.repository

import com.sopt.now.compose.data.datasource.FriendRemoteDataSource
import com.sopt.now.compose.domain.model.FriendsList
import com.sopt.now.compose.domain.repository.FriendRepository
import javax.inject.Inject

class FriendsRepositoryImpl @Inject constructor(
    private val friendRemoteDataSource: FriendRemoteDataSource,
) : FriendRepository {
    override suspend fun getFriendsList(page: Int): Result<FriendsList> =
        runCatching {
            friendRemoteDataSource.getFriendsList(page = page).toFriendsList()
        }
}