package com.sopt.now.presentation.Home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.model.Friend
import com.sopt.now.domain.model.FriendsList
import com.sopt.now.domain.model.User
import com.sopt.now.domain.repository.AuthRepository
import com.sopt.now.domain.repository.FriendRepository
import com.sopt.now.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val authRepository: AuthRepository,
    private val friendRepository: FriendRepository,
): ViewModel() {
    private val _homeUserState = MutableStateFlow<UiState<User>>(UiState.Empty)
    val homeUserState get() = _homeUserState.asStateFlow()

    private val _homeFriendState = MutableStateFlow<UiState<FriendsList>>(UiState.Empty)
    val homeFriendState get() = _homeFriendState.asStateFlow()

    fun getUserInfo(memberId: String) {
        viewModelScope.launch {
            _homeUserState.value = UiState.Loading
            authRepository.getUserInfo(memberId = memberId).onSuccess { user ->
                _homeUserState.value = UiState.Success(user)
            }.onFailure { exception: Throwable ->
                _homeUserState.value = UiState.Error(exception.message)
            }
        }
    }

    fun getFriendsInfo(page: Int) {
        viewModelScope.launch {
            _homeFriendState.value = UiState.Loading
            friendRepository.getFriendsList(page = page).onSuccess { friendsList ->
                _homeFriendState.value = UiState.Success(friendsList)
            }.onFailure { exception: Throwable ->
                _homeFriendState.value = UiState.Error(exception.message)
            }
        }
    }
}