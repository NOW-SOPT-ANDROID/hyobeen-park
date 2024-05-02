package com.sopt.now.Signup

import androidx.lifecycle.ViewModel
import com.sopt.now.R

class SignupViewModel : ViewModel() {
    fun checkSignupValidation(id: String, pw: String, nickname: String, mbti: String): Int {
        return when {
            !isSignupIdValid(id) -> R.string.signup_id_fail
            !isSignupPwValid(pw) -> R.string.signup_password_fail
            !isSignupNicknameValid(nickname) -> R.string.signup_nickname_fail
            !isSignupMbtiValid(mbti) -> R.string.signup_mbti_fail
            else -> R.string.signup_success
        }
    }

    private fun isSignupIdValid(id: String) = id.isNotBlank() && id.length in MIN_ID_LEN..MAX_ID_LEN
    private fun isSignupPwValid(pw: String) = pw.isNotBlank() && pw.length in MIN_PW_LEN..MAX_PW_LEN
    private fun isSignupNicknameValid(nickname: String) = nickname.isNotBlank()
    private fun isSignupMbtiValid(mbti: String) = mbti.isNotBlank()


    companion object {
        const val MIN_ID_LEN = 6
        const val MAX_ID_LEN = 10
        const val MIN_PW_LEN = 8
        const val MAX_PW_LEN = 12
    }
}