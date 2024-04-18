package com.sopt.now.Friend

import androidx.annotation.DrawableRes

data class Friend(
    @DrawableRes val profileImage :Int,
    val nickname : String,
    val mbti : String
)
