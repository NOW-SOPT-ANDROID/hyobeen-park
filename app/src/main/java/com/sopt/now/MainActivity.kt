package com.sopt.now

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.LoginActivity.Companion.ID
import com.sopt.now.LoginActivity.Companion.MBTI
import com.sopt.now.LoginActivity.Companion.NICKNAME

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTextViews()

    }

    private fun initTextViews() {
        intent.apply {
            with(binding) {
                tvMainNickname.setText(getStringExtra(NICKNAME))
                tvMainIdContent.setText(getStringExtra(ID))
                tvMainMbtiContent.setText(getStringExtra(MBTI))
            }
        }
    }
}