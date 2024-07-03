package com.sopt.now.compose.data.datasource

import com.sopt.now.compose.data.model.response.ResponseFriendsDto

interface FriendRemoteDataSource {
    suspend fun getFriendsList(page: Int): ResponseFriendsDto
}