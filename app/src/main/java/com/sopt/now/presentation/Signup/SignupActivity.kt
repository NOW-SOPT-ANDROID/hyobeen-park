package com.sopt.now.presentation.Signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.R
import com.sopt.now.databinding.ActivitySignupBinding
import com.sopt.now.presentation.Home.User.User
import com.sopt.now.presentation.Login.LoginActivity
import com.sopt.now.presentation.common.ViewModelFactory
import com.sopt.now.util.UiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var user: User

    private val signupViewModel: SignupViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignUpBtnClickListener()
        observeSignup()

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
        if (signupValidMsg == R.string.signup_success) postSignup(user, pw)
        else showToastMessage(signupValidMsg)
    }

    private fun postSignup(user: User, pw: String) {
        signupViewModel.postSignUp(user, pw)
    }

    private fun observeSignup() {
        signupViewModel.signUpState.flowWithLifecycle(lifecycle).onEach { signUpState ->
            when (signUpState) {
                is UiState.Success -> {
                    showToastMessage(R.string.signup_success)
                    startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                }

                is UiState.Error -> showToastMessage(R.string.server_connection_error)
                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun showToastMessage(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
