package com.sopt.now.compose.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto(
    @SerialName("authenticationId")
    val authentivationId: String,
    @SerialName("password")
    val password: String,
)
