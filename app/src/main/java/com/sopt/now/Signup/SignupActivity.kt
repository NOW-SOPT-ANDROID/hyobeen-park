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
import com.sopt.now.data.Key.PW
import com.sopt.now.data.Key.USER
import com.sopt.now.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
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
        // 입력 조건 + 공백 문자만 있지 않은지 확인
        val signupValidMsg = signupViewModel.checkSignupValidation(user.id, pw, user.nickname, user.phone)
        showToastMessage(signupValidMsg)
        Log.d("check-id", user.id.length.toString())
        if(signupValidMsg == R.string.signup_success) successSignUp(user, pw)
    }

    private fun successSignUp(user: User, pw: String) {
        showToastMessage(R.string.signup_success)

        Intent(this, LoginActivity::class.java).apply {
            putExtra(USER, user)
            putExtra(PW, pw)
            startActivity(this)
        }
    }


    private fun showToastMessage(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}