package com.sopt.now.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.now.BuildConfig.AUTH_BASE_URL
import com.sopt.now.BuildConfig.USER_API_URL
import com.sopt.now.data.service.AuthService
import com.sopt.now.data.service.UserService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object ApiFactory {
    fun retrofit(url: String) : Retrofit =
            Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .build()



    inline fun <reified T, B> create(url: B): T = retrofit(url.toString()).create(T::class.java)
}

object ServicePool {
    val authService = ApiFactory.create<AuthService, String>(AUTH_BASE_URL)
    val userService = ApiFactory.create<UserService, String>(USER_API_URL)
}