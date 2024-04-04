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

        // Toast 출력될 문자열
        var message : String?

        // 회원가입 정보 저장
        val id = binding.etSignupId.text
        val pw = binding.etSignupPw.text
        val nickname = binding.etSignupNickname.text
        val mbti = binding.etSignupMbti.text

        // 회원가입 버튼 클릭이벤트
        binding.btSignupSignup.setOnClickListener {
            // 입력 조건 + 공백 문자만 있지 않은지 확인
            when {
                id.isBlank() || id.length < 6 || id.length > 10 ->
                    message = "ID는 6자 이상, 10자 이하로 입력해주세요"
                pw.isBlank() || pw.length < 8 || pw.length > 12 ->
                    message = "비밀번호는 8자 이상, 12자 이하로 입력해주세요"
                nickname.isBlank() ->
                    message = "닉네임을 입력해주세요"
                mbti.isBlank() ->
                    message = "MBTI를 입력해주세요"
                else -> message = null
            }

            if(message == null) {
                // 입력 조건 모두 충족했을 경우 -> 회원가입 성공
                Toast.makeText(this, "회원가입에 성공했습니다!", Toast.LENGTH_SHORT).show()

                // intent에 정보 담아서 로그인 화면으로 전달
                Intent(this, LoginActivity::class.java).apply {
                    putExtra("id", id.toString())
                    putExtra("pw", pw.toString())
                    putExtra("nickname", nickname.toString())
                    putExtra("mbti", mbti.toString())
                    setResult(RESULT_OK, this)
                    finish()
                }
            } else {
                // 입력 조건을 충족하지 못한 경우 -> 토스트 메세지
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}