package com.sopt.now.compose

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.DTO.response.ResponseUserInfoDataDto
import com.sopt.now.compose.data.DTO.response.ResponseUserInfoDto
import com.sopt.now.compose.data.ServicePool
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel(){
    val authService by lazy { ServicePool.authService }
    private val _user = MutableStateFlow<ResponseUserInfoDataDto?>(null)
    val user get() = _user.asStateFlow()
    fun getUserData(userId: String) {
        authService.getUserInfo(userId).enqueue(object : Callback<ResponseUserInfoDto> {
            override fun onResponse(
                call: Call<ResponseUserInfoDto>,
                response: Response<ResponseUserInfoDto>
            ) {
                if (response.isSuccessful) {
                    val data: ResponseUserInfoDto? = response.body()
                    _user.value = data?.data
                } else {
                }
            }

            override fun onFailure(call: Call<ResponseUserInfoDto>, t: Throwable) {
            }
        })
    }

    fun getFriendsData() {

    }
}