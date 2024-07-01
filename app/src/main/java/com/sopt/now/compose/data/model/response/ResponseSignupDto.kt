package com.sopt.now.compose.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignupDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
)
