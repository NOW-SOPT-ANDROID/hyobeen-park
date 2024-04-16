package com.sopt.now

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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