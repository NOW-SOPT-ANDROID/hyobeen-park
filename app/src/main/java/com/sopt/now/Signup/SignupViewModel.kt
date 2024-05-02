package com.sopt.now.Signup

import androidx.lifecycle.ViewModel
import com.sopt.now.R
import java.util.regex.Pattern

class SignupViewModel : ViewModel() {
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

    companion object {
        const val MIN_ID_LEN = 6
        const val MAX_ID_LEN = 10
    }
}