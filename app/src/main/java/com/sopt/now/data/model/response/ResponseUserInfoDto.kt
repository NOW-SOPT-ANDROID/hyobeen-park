package com.sopt.now.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserInfoDto(
    @SerialName("authenticationId")
    val authenticationId: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("phone")
    val phone: String,
)