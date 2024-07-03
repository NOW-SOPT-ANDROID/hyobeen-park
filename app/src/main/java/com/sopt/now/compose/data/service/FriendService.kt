package com.sopt.now.compose.data.service

import com.sopt.now.compose.data.model.response.ResponseFriendsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendService {
    @GET("api/users")
    suspend fun getFriendsList(
        @Query("page") page: Int,
    ): ResponseFriendsDto
}