package com.sopt.now.data.datasource

import com.sopt.now.data.model.response.ResponseFriendsDto
import com.sopt.now.util.BaseResponse

interface FriendRemoteDataSource {
    suspend fun getFriendsList(page: Int): BaseResponse<ResponseFriendsDto>
}