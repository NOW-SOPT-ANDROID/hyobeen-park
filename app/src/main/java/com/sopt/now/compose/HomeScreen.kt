package com.sopt.now.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(nickname: String?, mbti: String?){
    val homeViewModel = HomeViewModel()
    val friendsList = homeViewModel.getFriendsData()

    LazyColumn {
        item {
            nickname?.let {
                mbti?.let { it1 ->
                    UserItem(
                        painter = R.drawable.ic_launcher_foreground,
                        contentDescription = "User Profile Image",
                        imageSize = 70.dp,
                        nickname = it,
                        mbti = it1,
                        fontSize = 24.sp
                    )
                }
            }
        }

        items(friendsList) {friendsList ->
            UserItem(
                painter = friendsList.profileImage,
                contentDescription = "Friends profile image",
                imageSize = 50.dp,
                nickname = friendsList.nickname,
                mbti = friendsList.mbti,
                fontSize = 18.sp
            )

        }
    }
}