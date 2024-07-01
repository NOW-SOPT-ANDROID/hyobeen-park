package com.sopt.now.compose.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignupDto(
    @SerialName("authenticationId")
    val authenticationId: String,
    @SerialName("password")
    val password: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("phone")
    val phone: String,
)
