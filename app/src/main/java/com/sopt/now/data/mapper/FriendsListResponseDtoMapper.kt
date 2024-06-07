package com.sopt.now.data.mapper

import com.sopt.now.data.model.response.ResponseFriendsDto
import com.sopt.now.domain.model.FriendsList

fun ResponseFriendsDto.toFriendsListModel() =
    FriendsList(
        friendsList = this.data.map { it.toFriendModel() }
    )