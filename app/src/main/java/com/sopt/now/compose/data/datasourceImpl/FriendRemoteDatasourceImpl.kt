package com.sopt.now.compose.data.datasourceImpl

import com.sopt.now.compose.data.datasource.FriendRemoteDataSource
import com.sopt.now.compose.data.model.response.ResponseFriendsDto
import com.sopt.now.compose.data.service.FriendService
import javax.inject.Inject

class FriendRemoteDatasourceImpl @Inject constructor(
    private val friendService: FriendService
) : FriendRemoteDataSource {
    override suspend fun getFriendsList(page: Int): ResponseFriendsDto =
        friendService.getFriendsList(page = page)
}