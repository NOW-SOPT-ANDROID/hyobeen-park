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

        val id = intent.getStringExtra("id")
        val nickname = intent.getStringExtra("nickname")
        val mbti = intent.getStringExtra("mbti")

        binding.tvMainNickname.setText(nickname)
        binding.tvMainIdcontent.setText(id)
        binding.tvMainMbticontent.setText(mbti)
    }
}