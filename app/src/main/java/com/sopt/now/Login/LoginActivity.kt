package com.sopt.now.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.Home.HomeActivity
import com.sopt.now.R
import com.sopt.now.Signup.SignupActivity
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var binding : ActivityLoginBinding
    private var id : String ?= null
    private var pw : String ?= null
    private var nickname : String ?= null
    private var mbti : String ?= null

    companion object{
        const val ID = "id"
        const val PW = "pw"
        const val NICKNAME = "nickname"
        const val MBTI = "mbti"
    }

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
                id = result.data?.getStringExtra(ID) ?: ""
                pw = result.data?.getStringExtra(PW) ?: ""
                nickname = result.data?.getStringExtra(NICKNAME) ?: ""
                mbti = result.data?.getStringExtra(MBTI) ?: ""
            }
        }
    }

    private fun initSignUpBtnClickListener() {
        // 회원가입 버튼 클릭이벤트
        binding.btnLoginSignup.setOnClickListener {
            Intent(this, SignupActivity::class.java).apply {
                resultLauncher.launch(this)
            }
        }
    }

    private fun initLoginBtnClickListener() {
        binding.btnLoginSignin.setOnClickListener {
            if (binding.etLoginId.text.toString() == id && binding.etLoginPw.text.toString() == pw) {
                // 로그인 성공
                Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show()
                Intent(this, HomeActivity::class.java).apply {
                    putExtra(ID, id)
                    putExtra(NICKNAME, nickname)
                    putExtra(MBTI, mbti)
                    startActivity(this)
                }
            } else {
                // 아이디 또는 비밀번호가 일치하지 않을 때
                Toast.makeText(this, R.string.login_fail, Toast.LENGTH_SHORT).show()
            }
        }
    }
}