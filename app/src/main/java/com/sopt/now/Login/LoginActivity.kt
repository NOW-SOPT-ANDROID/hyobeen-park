package com.sopt.now.Login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.Home.HomeActivity
import com.sopt.now.R
import com.sopt.now.Signup.SignupActivity
import com.sopt.now.User.User
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var pw: String? = null
    private var user: User? = null

    companion object {
        const val PW = "pw"
        const val USER = "user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSignUpResults()

        initSignUpBtnClickListener()
    }

    private fun getSignUpResults() {
        // 회원가입 정보 받아오기
        val user = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(USER, User::class.java)
        } else {
            intent.getParcelableExtra(USER)
        }

        if (user != null) {
            this.user = user
            pw = intent.getStringExtra(PW)
            initLoginBtnClickListener()
        }
    }

    private fun initSignUpBtnClickListener() {
        // 회원가입 버튼 클릭이벤트
        binding.btnLoginSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun initLoginBtnClickListener() {
        binding.btnLoginSignin.setOnClickListener {
            if (binding.etLoginId.text.toString() == user?.id && binding.etLoginPw.text.toString() == pw) {
                // 로그인 성공
                Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show()
                Intent(this, HomeActivity::class.java).apply {
                    putExtra(USER, user)
                    startActivity(this)
                }
            } else {
                // 아이디 또는 비밀번호가 일치하지 않을 때
                Toast.makeText(this, R.string.login_fail, Toast.LENGTH_SHORT).show()
            }
        }
    }
}