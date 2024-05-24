package com.sopt.now.presentation.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.R
import com.sopt.now.data.DTO.request.RequestLoginDto
import com.sopt.now.data.DTO.response.ResponseSignupDto
import com.sopt.now.data.ServicePool
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.presentation.Home.HomeActivity
import com.sopt.now.presentation.Signup.SignupActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val authService by lazy { ServicePool.authService }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignUpBtnClickListener()
        initLoginBtnClickListener()
    }

    private fun initSignUpBtnClickListener() {
        // 회원가입 버튼 클릭이벤트
        binding.btnLoginSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun initLoginBtnClickListener() {
        binding.btnLoginSignin.setOnClickListener {
            val loginRequest = RequestLoginDto(
                authenticationId = binding.etLoginId.text.toString(),
                password = binding.etLoginPw.text.toString(),
            )
            authService.login(loginRequest).enqueue(object : Callback<ResponseSignupDto> {
                override fun onResponse(
                    call: Call<ResponseSignupDto>,
                    response: Response<ResponseSignupDto>
                ) {
                    if (response.isSuccessful) {
                        val userId = response.headers()["location"]
                        Toast.makeText(
                            this@LoginActivity,
                            "$userId 님 로그인에 성공했습니다",
                            Toast.LENGTH_SHORT
                        ).show()
                        Intent(this@LoginActivity, HomeActivity::class.java).apply {
                            putExtra("userId", userId)
                            startActivity(this)
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, R.string.login_fail, Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<ResponseSignupDto>, t: Throwable) {
                    Toast.makeText(
                        this@LoginActivity,
                        R.string.server_connection_error,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

    }
}