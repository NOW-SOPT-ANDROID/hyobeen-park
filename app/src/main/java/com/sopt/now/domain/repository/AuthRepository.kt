package com.sopt.now.domain.repository

import com.sopt.now.data.model.request.RequestLoginDto
import com.sopt.now.data.model.request.RequestSignupDto

interface AuthRepository {
    suspend fun signUp(requestSignupDto: RequestSignupDto): Result<Unit>

    suspend fun login(requestLoginDto: RequestLoginDto): Result<Int?>
}