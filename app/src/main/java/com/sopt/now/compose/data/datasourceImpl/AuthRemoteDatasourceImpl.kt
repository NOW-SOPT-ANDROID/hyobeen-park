package com.sopt.now.compose.data.datasourceImpl

import com.sopt.now.compose.data.ServicePool
import com.sopt.now.compose.data.datasource.AuthRemoteDataSource
import com.sopt.now.compose.data.model.request.RequestLoginDto
import com.sopt.now.compose.data.model.request.RequestSignupDto
import com.sopt.now.compose.data.model.response.ResponseUserInfoDto
import com.sopt.now.compose.util.BaseResponse

class AuthRemoteDatasourceImpl : AuthRemoteDataSource {
    private val authService = ServicePool.authService

    override suspend fun signup(requestSignupDto: RequestSignupDto): BaseResponse<Unit> =
        authService.signup(requestSignupDto)

    override suspend fun login(requestLoginDto: RequestLoginDto): String? =
        authService.login(requestLoginDto).headers()[LOCATION]

    override suspend fun getUserInfo(memberId: String): BaseResponse<ResponseUserInfoDto> =
        authService.getUserInfo(memberId = memberId)

    companion object {
        const val LOCATION = "location"
    }
}