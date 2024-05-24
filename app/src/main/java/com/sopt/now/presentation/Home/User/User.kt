package com.sopt.now.presentation.Home.User

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @DrawableRes val profileImage :Int,
    val id : String,
    val nickname : String,
    val phone : String
): Parcelable
