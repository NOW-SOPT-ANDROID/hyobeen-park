package com.sopt.now.data.datasourceimpl

import com.sopt.now.data.ServicePool
import com.sopt.now.data.datasource.FriendRemoteDataSource
import com.sopt.now.data.model.response.ResponseFriendsDto
import com.sopt.now.util.BaseResponse

class FriendRemoteDataSourceImpl : FriendRemoteDataSource {
    private val friendService = ServicePool.friendService

    override suspend fun getFriendsList(page: Int): BaseResponse<ResponseFriendsDto> =
        friendService.getFriendsList(page = page)
}