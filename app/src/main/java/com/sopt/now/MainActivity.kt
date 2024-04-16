package com.sopt.now

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityMainBinding

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
                tvMainNickname.setText(getStringExtra("nickname"))
                tvMainIdcontent.setText(getStringExtra("id"))
                tvMainMbticontent.setText(getStringExtra("mbti"))
            }
        }
    }
}