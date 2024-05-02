package com.sopt.now.Signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.Login.LoginActivity
import com.sopt.now.R
import com.sopt.now.User.User
import com.sopt.now.data.DTO.request.RequestSignupDto
import com.sopt.now.data.DTO.response.ResponseSignupDto
import com.sopt.now.data.service.ServicePool
import com.sopt.now.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    private val authService by lazy { ServicePool.authService }
    private lateinit var binding: ActivitySignupBinding
    private lateinit var user: User

    private val signupViewModel by viewModels<SignupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignUpBtnClickListener()

    }

    private fun initSignUpBtnClickListener() {
        // 회원가입 버튼 클릭이벤트
        binding.btnSignupSignup.setOnClickListener {
            // 회원가입 정보 저장
            with(binding) {
                user = User(
                    R.drawable.ic_person_white_24,
                    etSignupId.text.toString(),
                    etSignupNickname.text.toString(),
                    etSignupPhone.text.toString()
                )
                checkInputValid(user, etSignupPw.text.toString())
            }
        }
    }

    private fun checkInputValid(user: User, pw: String) {
        // 입력 조건 확인
        val signupValidMsg =
            signupViewModel.checkSignupValidation(user.id, pw, user.nickname, user.phone)
        if (signupValidMsg == R.string.signup_success) successSignUp(user, pw)
        else showToastMessage(signupValidMsg)
    }

    private fun successSignUp(user: User, pw: String) {
        val signupRequest = getSignupRequestDto(user, pw)
        authService.signUp(signupRequest).enqueue(object : Callback<ResponseSignupDto> {
            override fun onResponse(
                call: Call<ResponseSignupDto>,
                response: Response<ResponseSignupDto>
            ) {
                if (response.isSuccessful) {
                    showToastMessage(R.string.signup_success)
                    startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                } else {
                    val error = response.message()
                    Toast.makeText(this@SignupActivity, error, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ResponseSignupDto>, t: Throwable) {
                Toast.makeText(this@SignupActivity, R.string.server_connection_error, Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun showToastMessage(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getSignupRequestDto(user: User, pw: String): RequestSignupDto {
        return RequestSignupDto(
            authenticationId = user.id,
            password = pw,
            nickname = user.nickname,
            phone = user.phone
        )
    }
}