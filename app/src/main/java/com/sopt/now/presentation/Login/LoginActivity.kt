package com.sopt.now.presentation.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.presentation.Home.HomeActivity
import com.sopt.now.presentation.Key.USERID
import com.sopt.now.presentation.Signup.SignupActivity
import com.sopt.now.presentation.common.ViewModelFactory
import com.sopt.now.util.UiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels { ViewModelFactory() }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignUpBtnClickListener()
        initLoginBtnClickListener()
        observeLogin()
    }

    private fun initSignUpBtnClickListener() {
        // 회원가입 버튼 클릭이벤트
        binding.btnLoginSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun initLoginBtnClickListener() {
        with(binding) {
            btnLoginSignin.setOnClickListener {
                loginViewModel.postLogin(etLoginId.text.toString(), etLoginPw.text.toString())
            }
        }
    }

    private fun observeLogin() {
        loginViewModel.loginState.flowWithLifecycle(lifecycle).onEach { loginState ->
            when (loginState) {
                is UiState.Success -> {
                    showToastMessage("${loginState.data} 님 로그인에 성공했습니다")
                    Intent(this@LoginActivity, HomeActivity::class.java).apply {
                        putExtra(USERID, loginState.data.toString())
                        startActivity(this)
                    }
                }

                is UiState.Error -> showToastMessage(loginState.message)
                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun showToastMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}