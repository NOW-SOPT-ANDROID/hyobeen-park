package com.sopt.now.domain.model

import androidx.annotation.DrawableRes

data class User(
    @DrawableRes val profileImage :Int,
    val id : String,
    val nickname : String,
    val phone : String
)
