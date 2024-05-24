package com.sopt.now.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.presentation.Home.HomeViewModel
import com.sopt.now.presentation.Login.LoginViewModel
import com.sopt.now.presentation.Mypage.MypageViewModel
import com.sopt.now.presentation.Signup.SignupViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory: ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel() as T
        } else if(modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel() as T
        } else if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel() as T
        } else if(modelClass.isAssignableFrom(MypageViewModel::class.java)) {
            return MypageViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}