package com.sopt.now.data.service

import com.sopt.now.data.model.request.RequestLoginDto
import com.sopt.now.data.model.request.RequestSignupDto
import com.sopt.now.data.model.response.ResponseSignupDto
import com.sopt.now.data.model.response.ResponseUserInfoDto
import com.sopt.now.util.BaseResponse
import com.sopt.now.util.NullableBaseResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun signUp(
        @Body request: RequestSignupDto,
    ): NullableBaseResponse<Unit>

    @POST("member/login")
    suspend fun login(
        @Body request: RequestLoginDto,
    ): Response<NullableBaseResponse<Unit>>

    @GET("member/info")
    fun getUserInfo(
        @Header("memberId") userId: String,
    ): Call<ResponseUserInfoDto>
}