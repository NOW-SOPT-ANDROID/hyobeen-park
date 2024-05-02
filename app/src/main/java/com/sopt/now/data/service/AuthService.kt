package com.sopt.now.data.service

import com.sopt.now.data.DTO.request.RequestSignupDto
import com.sopt.now.data.DTO.response.ResponseSignupDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request : RequestSignupDto,
    ): Call<ResponseSignupDto>
}