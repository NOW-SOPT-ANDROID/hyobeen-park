package com.sopt.now.Signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.Login.LoginActivity
import com.sopt.now.Login.LoginActivity.Companion.PW
import com.sopt.now.Login.LoginActivity.Companion.USER
import com.sopt.now.R
import com.sopt.now.User.User
import com.sopt.now.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var user: User
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
                    etSignupMbti.text.toString()
                )
                checkInputValid(user, etSignupPw.text.toString())
            }
        }
    }

    private fun checkInputValid(user: User, pw: String) {
        // 입력 조건 + 공백 문자만 있지 않은지 확인
        when {
            user.id.isBlank() || user.id.length !in 6..10 ->
                showToastMessage(R.string.signup_id_fail)

            pw.isBlank() || pw.length !in 8..12 ->
                showToastMessage(R.string.signup_password_fail)

            user.nickname.isBlank() ->
                showToastMessage(R.string.signup_nickname_fail)

            user.mbti.isBlank() ->
                showToastMessage(R.string.signup_mbti_fail)

            else -> successSignUp(user, pw)
        }
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