package com.sopt.now.compose.data.datasource

import com.sopt.now.compose.data.model.request.RequestLoginDto
import com.sopt.now.compose.data.model.request.RequestSignupDto
import com.sopt.now.compose.data.model.response.ResponseUserInfoDto
import com.sopt.now.compose.util.BaseResponse

interface AuthRemoteDataSource {
    suspend fun signup(requestSignupDto: RequestSignupDto): BaseResponse<Unit>

    suspend fun login(requestLoginDto: RequestLoginDto): String?

    suspend fun getUserInfo(memberId: String): BaseResponse<ResponseUserInfoDto>
}