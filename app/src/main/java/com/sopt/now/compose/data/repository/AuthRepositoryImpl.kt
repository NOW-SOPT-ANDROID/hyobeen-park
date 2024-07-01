package com.sopt.now.compose.data.repository

import com.sopt.now.compose.data.datasource.AuthRemoteDataSource
import com.sopt.now.compose.data.model.request.RequestLoginDto
import com.sopt.now.compose.data.model.request.RequestSignupDto
import com.sopt.now.compose.domain.model.User
import com.sopt.now.compose.domain.repository.AuthRepository

class AuthRepositoryImpl (
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {
    override suspend fun signUp(requestSignupDto: RequestSignupDto): Result<Unit> =
        runCatching {
            authRemoteDataSource.signup(requestSignupDto = requestSignupDto)
        }

    override suspend fun login(requestLoginDto: RequestLoginDto): Result<Int?> =
        runCatching {
            authRemoteDataSource.login(requestLoginDto = requestLoginDto)?.toInt()
        }

    override suspend fun getUserInfo(memberId: String): Result<User> =
        runCatching {
            authRemoteDataSource.getUserInfo(memberId = memberId).data?.toUserEntity()!!
        }
}