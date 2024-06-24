package com.sopt.now.compose.presentation.ui.main.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.presentation.component.UserItem

@Composable
fun HomeScreen(userId: String) {
    val homeViewModel = HomeViewModel()

    val friendsList = homeViewModel.getFriendsData()
    val user = homeViewModel.user.collectAsState()

    homeViewModel.getUserData(userId)

    LazyColumn {
        item {
            UserItem(
                painter = R.drawable.ic_launcher_foreground,
                contentDescription = "User Profile Image",
                imageSize = 70.dp,
                nickname = user.value?.nickname ?: "닉네임",
                phone = user.value?.phone ?: "phone",
                fontSize = 24.sp
            )
        }

//        items(friendsList) { friendsList ->
//            UserItem(
//                painter = friendsList.profileImage,
//                contentDescription = "Friends profile image",
//                imageSize = 50.dp,
//                nickname = friendsList.nickname,
//                phone = friendsList.phone,
//                fontSize = 18.sp
//            )
//
//        }
    }
}