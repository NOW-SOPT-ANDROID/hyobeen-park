package com.sopt.now

import androidx.annotation.DrawableRes

data class Friend(
    @DrawableRes val profileImage :Int,
    val nickname : String,
    val mbti : String
)
