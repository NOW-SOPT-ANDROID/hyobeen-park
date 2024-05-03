package com.sopt.now.compose

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.data.DTO.response.ResponseUserInfoDataDto
import com.sopt.now.compose.data.DTO.response.ResponseUserInfoDto
import com.sopt.now.compose.data.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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