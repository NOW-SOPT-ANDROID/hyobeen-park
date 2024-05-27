package com.sopt.now.data.repository

import com.sopt.now.data.model.request.RequestSignupDto
import com.sopt.now.data.datasource.AuthRemoteDataSource
import com.sopt.now.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {
    override suspend fun signUp(requestSignupDto: RequestSignupDto): Result<Unit> =
        runCatching {
            authRemoteDataSource.signUp(requestSignupDto = requestSignupDto)
        }
}