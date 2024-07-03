package com.sopt.now.compose.presentation.ui.main.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.now.compose.R
import com.sopt.now.compose.domain.model.Friend
import com.sopt.now.compose.domain.model.FriendsList
import com.sopt.now.compose.presentation.component.UserItem
import com.sopt.now.compose.util.UiState

@Composable
fun HomeGetData(homeViewModel: HomeViewModel = hiltViewModel(), memberId: String = "1") {
    val friendState by homeViewModel.homeFriendState.collectAsState()

    when (friendState) {
        is UiState.Empty -> {}
        is UiState.Loading -> {}
        is UiState.Error -> {}
        is UiState.Success -> {
            HomeScreen(friendList = (friendState as UiState.Success<FriendsList>).data.friendsList)
        }
    }
}

@Composable
private fun HomeScreen(friendList: List<Friend>) {
    LazyColumn {
        item() {
            UserItem(
                painter = R.drawable.ic_launcher_foreground,
                contentDescription = "User Profile Image",
                imageSize = 70.dp,
                nickname = "터닝안드효빈",
                phone = "❤️",
                fontSize = 24.sp
            )
        }
        items(friendList) { friendsList ->
            UserItem(
                painter = friendsList.avatar,
                contentDescription = "Friends profile image",
                imageSize = 50.dp,
                nickname = friendsList.firstName,
                phone = friendsList.email,
                fontSize = 16.sp
            )
        }
    }
}
