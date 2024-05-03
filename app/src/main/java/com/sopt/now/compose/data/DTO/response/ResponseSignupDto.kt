package com.sopt.now.compose.data.DTO.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignupDto(
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
)
