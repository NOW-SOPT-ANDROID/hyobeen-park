package com.sopt.now.data.mapper

import com.sopt.now.R
import com.sopt.now.data.model.response.ResponseUserInfoDto
import com.sopt.now.domain.model.User

fun ResponseUserInfoDto.toUserModel() =
    User(
        profileImage = R.drawable.ic_person_white_24,
        id = this.authenticationId,
        nickname = this.nickname,
        phone = this.phone,
    )