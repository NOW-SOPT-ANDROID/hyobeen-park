package com.sopt.now

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.databinding.ActivitySignupBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var binding : ActivityLoginBinding
    private var id = ""
    private var pw = ""
    private var nickname = ""
    private var mbti = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 회원가입 버튼 클릭이벤트
        binding.btLoginSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            resultLauncher.launch(intent)
        }

        // 회원가입 정보 받아오기
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                id = result.data?.getStringExtra("id") ?: ""
                pw = result.data?.getStringExtra("pw") ?: ""
                nickname = result.data?.getStringExtra("nickname") ?: ""
                mbti = result.data?.getStringExtra("mbti") ?: ""
            }
        }

        binding.btLoginSignin.setOnClickListener {
            // 아이디, 비밀번호 입력 여부 확인
            if(binding.etLoginId.text.isBlank()) {
                Toast.makeText(this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if (binding.etLoginPw.text.isBlank()) {
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if (binding.etLoginId.text.toString() == id && binding.etLoginPw.text.toString() == pw) {
                // 로그인 성공
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("nickname", nickname)
                intent.putExtra("mbti", mbti)
                startActivity(intent)
            } else {
                // 아이디 또는 비밀번호가 일치하지 않을 때
                Toast.makeText(this, "아이디와 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}