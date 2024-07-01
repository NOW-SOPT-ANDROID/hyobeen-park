package com.sopt.now.compose.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.now.compose.BuildConfig.AUTH_BASE_URL
import com.sopt.now.compose.BuildConfig.USER_API_URL
import com.sopt.now.compose.data.service.AuthService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object ApiFactory {
    fun retrofit(url: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

    inline fun <reified T, B> create(url: B): T = retrofit(url.toString()).create(T::class.java)
}

object ServicePool {
    val authService = ApiFactory.create<AuthService, String>(AUTH_BASE_URL)
    val userService = ApiFactory.create<AuthService, String>(USER_API_URL)
}