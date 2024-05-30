package com.sopt.now.data.mapper

import com.sopt.now.data.model.response.ResponseFriendsDto.ResponseFriend
import com.sopt.now.domain.model.Friend

fun ResponseFriend.toFriendModel() =
    Friend(
        id = this.id,
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        avatar = this.avatar
    )