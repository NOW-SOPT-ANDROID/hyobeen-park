package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
                showToastMessage("ID는 6자 이상, 10자 이하로 입력해주세요")
            pw.isBlank() || pw.length !in 8..12 ->
                showToastMessage("비밀번호는 8자 이상, 12자 이하로 입력해주세요")
            nickname.isBlank() ->
                showToastMessage("닉네임을 입력해주세요")
            mbti.isBlank() ->
                showToastMessage("MBTI를 입력해주세요")
            else -> successSignUp(id, pw, nickname, mbti)
        }
    }

    private fun successSignUp(id : String, pw : String, nickname : String, mbti : String) {
        showToastMessage("회원가입에 성공했습니다!")

        Intent(this, LoginActivity::class.java).apply {
            putExtra("id", id)
            putExtra("pw", pw)
            putExtra("nickname", nickname)
            putExtra("mbti", mbti)
            setResult(RESULT_OK, this)
            finish()
        }
    }


    private fun showToastMessage(message : String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}