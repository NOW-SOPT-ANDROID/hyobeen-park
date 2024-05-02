package com.sopt.now.Friend

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding): RecyclerView.ViewHolder(binding.root){
    fun onBind(friendData: Friend) {
        binding.run {
            ivFriendProfileImg.setImageResource(friendData.profileImage)
            tvFriendNickname.text = friendData.nickname
            tvFriendMbti.text = friendData.mbti
        }
    }
}