package com.sopt.now.compose.data.model.response

import com.sopt.now.compose.domain.model.Friend
import com.sopt.now.compose.domain.model.FriendsList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFriendsDto(
    @SerialName("page")
    val page: Int = 2,
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
    ) {
        fun toFriendEntity(): Friend =
            Friend(
                id = this.id,
                email = this.email,
                firstName = this.firstName,
                lastName = this.lastName,
                avatar = this.avatar
            )
    }

    @Serializable
    data class ResponseFriendsListSupportDto(
        @SerialName("url")
        val url: String,
        @SerialName("text")
        val text: String,
    )

    fun toFriendsList(): FriendsList =
        FriendsList(
            friendsList = this.data.map { it.toFriendEntity() }
        )
}
