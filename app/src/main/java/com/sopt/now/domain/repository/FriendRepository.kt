package com.sopt.now.domain.repository

import com.sopt.now.domain.model.FriendsList

interface FriendRepository {
    suspend fun getFriendsList(page: Int): Result<FriendsList>
}