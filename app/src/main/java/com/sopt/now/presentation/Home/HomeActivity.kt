package com.sopt.now.presentation.Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sopt.now.presentation.Mypage.MypageFragment
import com.sopt.now.R
import com.sopt.now.presentation.Search.SearchFragment
import com.sopt.now.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentFragment = supportFragmentManager.findFragmentById(binding.fcvHome.id)
        if(currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.fcvHome.id, HomeFragment())
                .commit()
        }

        clickBottomNavigation()
    }

    private fun clickBottomNavigation() {
        binding.bnvHome.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_search -> {
                    replaceFragment(SearchFragment())
                    true
                }

                R.id.menu_mypage -> {
                    replaceFragment(MypageFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_home, fragment)
            .commit()
    }
}