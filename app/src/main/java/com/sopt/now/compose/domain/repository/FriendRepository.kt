package com.sopt.now.compose.domain.repository

import com.sopt.now.compose.domain.model.FriendsList

interface FriendRepository {
    suspend fun getFriendsList(page: Int): Result<FriendsList>
}