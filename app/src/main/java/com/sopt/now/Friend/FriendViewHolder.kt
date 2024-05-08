package com.sopt.now.Friend

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.now.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding): RecyclerView.ViewHolder(binding.root){
    fun onBind(friendData: Friend) {
        binding.run {
            Glide.with(root.context)
                .load(friendData.avatar)
                .into(ivFriendProfileImg)
            tvFriendNickname.text = friendData.firstName
            tvFriendPhone.text = friendData.email
        }
    }
}