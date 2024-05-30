package com.sopt.now.data.repository

import com.sopt.now.data.datasource.FriendRemoteDataSource
import com.sopt.now.data.mapper.toFriendsListModel
import com.sopt.now.data.model.response.ResponseFriendsDto
import com.sopt.now.data.service.FriendService
import com.sopt.now.domain.model.FriendsList
import com.sopt.now.domain.repository.FriendRepository

class FriendRepositoryImpl(
    private val friendRemoteDataSource: FriendRemoteDataSource
): FriendRepository {
    override suspend fun getFriendsList(page: Int): Result<FriendsList> =
        runCatching {
            friendRemoteDataSource.getFriendsList(page = page).data.toFriendsListModel()
        }
}