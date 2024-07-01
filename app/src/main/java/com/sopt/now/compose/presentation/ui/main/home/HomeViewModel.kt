package com.sopt.now.compose.presentation.ui.main.home

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.ServicePool
import com.sopt.now.compose.data.model.response.ResponseUserInfoDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    val authService by lazy { ServicePool.authService }
    private val _user = MutableStateFlow<ResponseUserInfoDto.ResponseUserInfoDataDto?>(null)
    val user get() = _user.asStateFlow()
//    fun getUserData(userId: String) {
//        authService.getUserInfo(userId).enqueue(object : Callback<ResponseUserInfoDto> {
//            override fun onResponse(
//                call: Call<ResponseUserInfoDto>,
//                response: Response<ResponseUserInfoDto>
//            ) {
//                if (response.isSuccessful) {
//                    val data: ResponseUserInfoDto? = response.body()
//                    _user.value = data?.data
//                } else {
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseUserInfoDto>, t: Throwable) {
//            }
//        })
//    }

    fun getFriendsData() {

    }
}