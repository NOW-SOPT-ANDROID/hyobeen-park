package com.sopt.now.compose.presentation.model

import androidx.annotation.DrawableRes

data class User(
    @DrawableRes val profileImage: Int,
    val nickname: String,
    val phone: String
)
