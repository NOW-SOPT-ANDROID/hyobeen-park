package com.sopt.now.data.service

import com.sopt.now.data.model.response.ResponseFriendsDto
import com.sopt.now.util.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendService {
    @GET("api/users")
    suspend fun getFriendsList(
        @Query("page") page: Int,
    ): ResponseFriendsDto
}