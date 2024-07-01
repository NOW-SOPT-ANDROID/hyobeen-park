package com.sopt.now.compose.data.model.response

import com.sopt.now.compose.R
import com.sopt.now.compose.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserInfoDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: ResponseUserInfoDataDto,
) {
    @Serializable
    data class ResponseUserInfoDataDto(
        @SerialName("authenticationId")
        val authenticationId: String,
        @SerialName("nickname")
        val nickname: String,
        @SerialName("phone")
        val phone: String,
    )

    fun toUserEntity(): User =
        User(
            profileImage = R.drawable.ic_baseline_visibility_white_24,
            id = data.authenticationId,
            nickname = data.nickname,
            phone = data.phone,
        )
}