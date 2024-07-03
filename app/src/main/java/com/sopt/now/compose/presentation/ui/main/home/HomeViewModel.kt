package com.sopt.now.compose.presentation.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.domain.model.FriendsList
import com.sopt.now.compose.domain.model.User
import com.sopt.now.compose.domain.repository.FriendRepository
import com.sopt.now.compose.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val friendRepository: FriendRepository,
) : ViewModel() {
    private val _homeUserState = MutableStateFlow<UiState<User>>(UiState.Empty)
    val homeUserState get() = _homeUserState.asStateFlow()

    private val _homeFriendState = MutableStateFlow<UiState<FriendsList>>(UiState.Empty)
    val homeFriendState get() = _homeFriendState.asStateFlow()

    init {
        getFriendsInfo(2)
    }

    fun getFriendsInfo(page: Int) {
        _homeFriendState.value = UiState.Loading
        viewModelScope.launch {
            friendRepository.getFriendsList(page = page).onSuccess { friendsList ->
                _homeFriendState.value = UiState.Success(friendsList)
            }.onFailure { exception: Throwable ->
                _homeFriendState.value = UiState.Error(exception.message)
            }
        }
    }
}