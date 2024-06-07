package com.sopt.now.presentation.Signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.R
import com.sopt.now.data.model.request.RequestSignupDto
import com.sopt.now.domain.repository.AuthRepository
import com.sopt.now.presentation.Home.User.User
import com.sopt.now.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class SignupViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _signUpState = MutableStateFlow<UiState<User>>(UiState.Empty)
    val signUpState get() = _signUpState.asStateFlow()

    private var phone_pattern = "^01([0|1|6|7|8|9])-([0-9]{4})-([0-9]{4})$"
    private var pw_pattern = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,12}$"

    fun checkSignupValidation(id: String, pw: String, nickname: String, phone: String): Int {
        return when {
            !isSignupIdValid(id) -> R.string.signup_id_fail
            !isSignupPwValid(pw) -> R.string.signup_password_fail
            !isSignupNicknameValid(nickname) -> R.string.signup_nickname_fail
            !isSignupPhoneValid(phone) -> R.string.signup_phone_fail
            else -> R.string.signup_success
        }
    }

    private fun isSignupIdValid(id: String) = id.isNotBlank() && id.length in MIN_ID_LEN..MAX_ID_LEN
    private fun isSignupPwValid(pw: String) = Pattern.matches(pw_pattern, pw)
    private fun isSignupNicknameValid(nickname: String) = nickname.isNotBlank()
    private fun isSignupPhoneValid(phone: String) = Pattern.matches(phone_pattern, phone)

    fun postSignUp(user: User, pw: String) {
        val signupDto = RequestSignupDto(user.id, pw, user.nickname, user.phone)
        viewModelScope.launch {
            _signUpState.value = UiState.Loading
            authRepository.signUp(signupDto).onSuccess {
                _signUpState.value = UiState.Success(user)
            }.onFailure { exception: Throwable ->
                _signUpState.value = UiState.Error(exception.message)

            }
        }
    }

    companion object {
        const val MIN_ID_LEN = 6
        const val MAX_ID_LEN = 10
    }
}