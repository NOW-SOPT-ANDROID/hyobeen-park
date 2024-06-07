package com.sopt.now.presentation.Mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.model.User
import com.sopt.now.domain.repository.AuthRepository
import com.sopt.now.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MypageViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _mypageState = MutableStateFlow<UiState<User>>(UiState.Empty)
    val mypageState get() = _mypageState.asStateFlow()

    fun getUserInfo(memberId: String) {
        viewModelScope.launch {
            _mypageState.value = UiState.Loading
            authRepository.getUserInfo(memberId = memberId).onSuccess { user ->
                _mypageState.value = UiState.Success(user)
            }.onFailure { exception: Throwable ->
                _mypageState.value = UiState.Error(exception.message)
            }
        }
    }
}