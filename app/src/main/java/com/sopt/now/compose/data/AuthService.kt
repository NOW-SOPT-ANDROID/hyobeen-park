package com.sopt.now.compose.data

import com.sopt.now.compose.data.DTO.request.RequestSignupDto
import com.sopt.now.compose.data.DTO.response.ResponseSignupDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signup(
        @Body request: RequestSignupDto,
    ): Call<ResponseSignupDto>
}