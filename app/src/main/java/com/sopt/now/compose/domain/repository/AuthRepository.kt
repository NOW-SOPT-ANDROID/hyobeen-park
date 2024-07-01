package com.sopt.now.compose.domain.repository

import com.sopt.now.compose.data.model.request.RequestLoginDto
import com.sopt.now.compose.data.model.request.RequestSignupDto
import com.sopt.now.compose.domain.model.User
import dagger.Binds

interface AuthRepository {
    @Binds
    suspend fun signUp(requestSignupDto: RequestSignupDto): Result<Unit>

    suspend fun login(requestLoginDto: RequestLoginDto): Result<Int?>

    suspend fun getUserInfo(memberId: String): Result<User>
}