package com.sopt.now.presentation.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.model.request.RequestLoginDto
import com.sopt.now.domain.repository.AuthRepository
import com.sopt.now.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _loginState = MutableStateFlow<UiState<Int?>>(UiState.Empty)
    val loginState get() = _loginState.asStateFlow()

    fun postLogin(authenticationId: String, password: String) {
        val loginDto = RequestLoginDto(authenticationId, password)
        viewModelScope.launch {
            _loginState.value = UiState.Loading
            authRepository.login(loginDto).onSuccess { userId ->
                _loginState.value = UiState.Success(userId)
            }.onFailure { exception: Throwable ->
                _loginState.value = UiState.Error(exception.message)
            }
        }
    }
}