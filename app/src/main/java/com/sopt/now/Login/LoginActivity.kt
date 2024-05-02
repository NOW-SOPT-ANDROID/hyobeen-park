package com.sopt.now.Login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.Home.HomeActivity
import com.sopt.now.R
import com.sopt.now.Signup.SignupActivity
import com.sopt.now.User.User
import com.sopt.now.data.DTO.request.RequestLoginDto
import com.sopt.now.data.DTO.request.RequestSignupDto
import com.sopt.now.data.DTO.response.ResponseSignupDto
import com.sopt.now.data.Key.PW
import com.sopt.now.data.Key.USER
import com.sopt.now.data.service.ServicePool
import com.sopt.now.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val authService by lazy { ServicePool.authService }
    private lateinit var binding: ActivityLoginBinding
    private var pw: String? = null
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLoginBtnClickListener()
        initSignUpBtnClickListener()
    }

    private fun initSignUpBtnClickListener() {
        // 회원가입 버튼 클릭이벤트
        binding.btnLoginSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun initLoginBtnClickListener() {
        binding.btnLoginSignin.setOnClickListener {
            val loginRequest = getLoginRequestDto(binding.etLoginId.text.toString(), binding.etLoginPw.text.toString())
            authService.login(loginRequest).enqueue(object : Callback<ResponseSignupDto> {
                override fun onResponse(
                    call: Call<ResponseSignupDto>,
                    response: Response<ResponseSignupDto>
                ) {
                    if(response.isSuccessful) {
                        val data: ResponseSignupDto? = response.body()
                        val userId = response.headers()["location"]
                        Toast.makeText(this@LoginActivity, "$userId 님 로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                        Intent(this@LoginActivity, HomeActivity::class.java).apply {
                            putExtra("userId", userId)
                            startActivity(this)
                        }
                    } else {
                        val error = response.message()
                        Toast.makeText(this@LoginActivity, error, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseSignupDto>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "서버 에러 발생", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }

    private fun getLoginRequestDto(id: String, pw: String): RequestLoginDto {
        return RequestLoginDto(
            authenticationId = id,
            password = pw,
        )
    }
}