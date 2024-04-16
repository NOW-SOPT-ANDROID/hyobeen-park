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
    private var id : String ?= null
    private var pw : String ?= null
    private var nickname : String ?= null
    private var mbti : String ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSignUpResults()
        initLoginBtnClickListener()
        initSignUpBtnClickListener()
    }

    private fun getSignUpResults() {
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
    }

    private fun initSignUpBtnClickListener() {
        // 회원가입 버튼 클릭이벤트
        binding.btnLoginSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun initLoginBtnClickListener() {
        binding.btnLoginSignin.setOnClickListener {
            if (binding.etLoginId.text.toString() == id && binding.etLoginPw.text.toString() == pw) {
                // 로그인 성공
                Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                Intent(this, MainActivity::class.java).apply {
                    putExtra("id", id)
                    putExtra("nickname", nickname)
                    putExtra("mbti", mbti)
                    startActivity(this)
                }
            } else {
                // 아이디 또는 비밀번호가 일치하지 않을 때
                Toast.makeText(this, "아이디와 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}