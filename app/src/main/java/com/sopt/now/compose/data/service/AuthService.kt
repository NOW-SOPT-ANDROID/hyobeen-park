package com.sopt.now.compose.data.service

import com.sopt.now.compose.data.model.request.RequestLoginDto
import com.sopt.now.compose.data.model.request.RequestSignupDto
import com.sopt.now.compose.data.model.response.ResponseSignupDto
import com.sopt.now.compose.data.model.response.ResponseUserInfoDto
import com.sopt.now.compose.util.BaseResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun signup(
        @Body request: RequestSignupDto,
    ): BaseResponse<Unit>

    @POST("member/login")
    suspend fun login(
        @Body request: RequestLoginDto,
    ): Response<BaseResponse<Unit>>

    @GET("member/info")
    suspend fun getUserInfo(
        @Header("memberId") memberId: String,
    ): BaseResponse<ResponseUserInfoDto>
}