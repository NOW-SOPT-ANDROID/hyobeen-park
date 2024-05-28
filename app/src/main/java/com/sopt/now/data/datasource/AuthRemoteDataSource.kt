package com.sopt.now.data.datasource

import com.sopt.now.data.model.request.RequestLoginDto
import com.sopt.now.data.model.request.RequestSignupDto
import com.sopt.now.data.model.response.ResponseUserInfoDto
import com.sopt.now.util.BaseResponse
import com.sopt.now.util.NullableBaseResponse

interface AuthRemoteDataSource {
    suspend fun signUp(requestSignupDto: RequestSignupDto): NullableBaseResponse<Unit>

    suspend fun login(requestLoginDto: RequestLoginDto): String?

    suspend fun getUserInfo(memberId: String): BaseResponse<ResponseUserInfoDto>
}