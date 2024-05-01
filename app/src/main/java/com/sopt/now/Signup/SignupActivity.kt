package com.sopt.now.Signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.Login.LoginActivity
import com.sopt.now.Login.LoginActivity.Companion.ID
import com.sopt.now.Login.LoginActivity.Companion.MBTI
import com.sopt.now.Login.LoginActivity.Companion.NICKNAME
import com.sopt.now.Login.LoginActivity.Companion.PW
import com.sopt.now.R
import com.sopt.now.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignupBinding
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
                val id = etSignupId.text.toString()
                val pw = etSignupPw.text.toString()
                val nickname = etSignupNickname.text.toString()
                val mbti = etSignupMbti.text.toString()
                checkInputValid(id, pw, nickname, mbti)
            }
        }
    }

    private fun checkInputValid(id : String, pw : String, nickname : String, mbti : String) {
        // 입력 조건 + 공백 문자만 있지 않은지 확인
        when {
            id.isBlank() || id.length !in 6..10 ->
                showToastMessage(R.string.signup_id_fail)
            pw.isBlank() || pw.length !in 8..12 ->
                showToastMessage(R.string.signup_password_fail)
            nickname.isBlank() ->
                showToastMessage(R.string.signup_nickname_fail)
            mbti.isBlank() ->
                showToastMessage(R.string.signup_mbti_fail)
            else -> successSignUp(id, pw, nickname, mbti)
        }
    }

    private fun successSignUp(id : String, pw : String, nickname : String, mbti : String) {
        showToastMessage(R.string.signup_success)

        Intent(this, LoginActivity::class.java).apply {
            putExtra(ID, id)
            putExtra(PW, pw)
            putExtra(NICKNAME, nickname)
            putExtra(MBTI, mbti)
            setResult(RESULT_OK, this)
            finish()
        }
    }


    private fun showToastMessage(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}