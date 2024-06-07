package com.sopt.now.domain.repository

import com.sopt.now.data.model.request.RequestLoginDto
import com.sopt.now.data.model.request.RequestSignupDto
import com.sopt.now.domain.model.User

interface AuthRepository {
    suspend fun signUp(requestSignupDto: RequestSignupDto): Result<Unit>

    suspend fun login(requestLoginDto: RequestLoginDto): Result<Int?>

    suspend fun getUserInfo(memberId: String): Result<User>
}