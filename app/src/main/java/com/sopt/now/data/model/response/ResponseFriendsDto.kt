package com.sopt.now.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFriendsDto(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("data")
    val data: List<ResponseFriend>,
    @SerialName("support")
    val support: ResponseFriendsListSupportDto,
) {
    @Serializable
    data class ResponseFriend(
        @SerialName("id")
        val id: Int,
        @SerialName("email")
        val email: String,
        @SerialName("first_name")
        val firstName: String,
        @SerialName("last_name")
        val lastName: String,
        @SerialName("avatar")
        val avatar: String,
    )

    @Serializable
    data class ResponseFriendsListSupportDto(
        @SerialName("url")
        val url: String,
        @SerialName("text")
        val text: String,
    )
}

