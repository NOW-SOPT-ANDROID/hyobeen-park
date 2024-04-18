package com.sopt.now.User

import androidx.annotation.DrawableRes

data class User(
    @DrawableRes val profileImage :Int,
    val nickname : String,
    val mbti : String
)
