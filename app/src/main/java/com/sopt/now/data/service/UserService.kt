package com.sopt.now.data.service

import com.sopt.now.data.model.response.ResponseUserListDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api/users")
    fun getUserList(
        @Query("page") page: Int,
    ): Call<ResponseUserListDto>
}