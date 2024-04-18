package com.sopt.now

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemUserBinding

class UserViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root){
    fun onBind(userData: User) {
        binding.run {
            ivUserProfileImg.setImageResource(userData.profileImage)
            tvUserNickname.text = userData.nickname
            tvUserMbti.text = userData.mbti
        }
    }
}