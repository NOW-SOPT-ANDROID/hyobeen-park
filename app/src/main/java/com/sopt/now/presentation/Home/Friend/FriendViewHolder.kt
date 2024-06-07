package com.sopt.now.presentation.Home.Friend

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.domain.model.Friend

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