package com.sopt.now.data.service

import com.sopt.now.data.DTO.request.RequestLoginDto
import com.sopt.now.data.DTO.request.RequestSignupDto
import com.sopt.now.data.DTO.response.ResponseSignupDto
import com.sopt.now.data.DTO.response.ResponseUserInfoDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignupDto,
    ): Call<ResponseSignupDto>

    @POST("member/login")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<ResponseSignupDto>

    @GET("member/info")
    fun getUserInfo(
        @Header("memberId") userId: String,
    ): Call<ResponseUserInfoDto>
}