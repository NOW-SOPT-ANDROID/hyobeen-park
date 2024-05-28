package com.sopt.now.data.datasourceimpl

import com.sopt.now.data.ServicePool
import com.sopt.now.data.datasource.AuthRemoteDataSource
import com.sopt.now.data.model.request.RequestLoginDto
import com.sopt.now.data.model.request.RequestSignupDto
import com.sopt.now.util.NullableBaseResponse

class AuthRemoteDataSourceImpl : AuthRemoteDataSource {
    private val authService = ServicePool.authService

    override suspend fun signUp(requestSignupDto: RequestSignupDto): NullableBaseResponse<Unit> =
        authService.signUp(requestSignupDto)

    override suspend fun login(requestLoginDto: RequestLoginDto): NullableBaseResponse<Unit> =
        authService.login(requestLoginDto)
}