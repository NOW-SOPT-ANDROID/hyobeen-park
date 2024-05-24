package com.sopt.now.compose

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.DTO.request.RequestSignupDto
import com.sopt.now.compose.data.DTO.response.ResponseSignupDto
import com.sopt.now.compose.data.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class SignupViewModel : ViewModel() {
    private var phone_pattern = "^01([0|1|6|7|8|9])-([0-9]{4})-([0-9]{4})$"
    private var pw_pattern = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,12}$"
    val authService by lazy { ServicePool.authService }
    val liveData = MutableLiveData<SignupState>()
    fun checkSignupValidation(id: String, pw: String, nickname: String, phone: String): Int {
        return when {
            !isSignupIdValid(id) -> R.string.signup_id_fail
            !isSignupPwValid(pw) -> R.string.signup_password_fail
            !isSignupNicknameValid(nickname) -> R.string.signup_nickname_fail
            !isSignupPhoneValid(phone) -> R.string.signup_phone_fail
            else -> R.string.signup_success
        }
    }

    fun postSignup(requestSignupDto: RequestSignupDto){
        authService.signup(requestSignupDto).enqueue(object : Callback<ResponseSignupDto> {
            override fun onResponse(
                call: Call<ResponseSignupDto>,
                response: Response<ResponseSignupDto>
            ) {
                if (response.isSuccessful) {
                    liveData.value = SignupState(true, "회원가입에 성공했습니다")
                } else {
                    liveData.value = SignupState(true, "회원가입에 실패했습니다")
                }
            }

            override fun onFailure(call: Call<ResponseSignupDto>, t: Throwable) {
                liveData.value = SignupState(true, "서버 통신 에러")
            }
        })
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