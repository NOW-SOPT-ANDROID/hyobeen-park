package com.sopt.now.data.DTO.response

import com.sopt.now.presentation.Home.Friend.Friend
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserListDto(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("data")
    val data: List<Friend>,
    @SerialName("support")
    val support: ResponseUserListSupportDto,
)

@Serializable
data class ResponseUserListSupportDto(
    @SerialName("url")
    val url: String,
    @SerialName("text")
    val text: String,
)