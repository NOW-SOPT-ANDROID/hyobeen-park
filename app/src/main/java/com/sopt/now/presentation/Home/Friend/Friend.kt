package com.sopt.now.presentation.Home.Friend

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Friend(
    @SerialName("id")
    val id: Int,
    @SerialName("email")
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("avatar")
    val avatar: String,
)