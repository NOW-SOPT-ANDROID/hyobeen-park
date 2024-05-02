package com.sopt.now.Home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.Friend.FriendAdapter
import com.sopt.now.R
import com.sopt.now.User.User
import com.sopt.now.User.UserAdapter
import com.sopt.now.data.DTO.response.ResponseUserInfoDataDto
import com.sopt.now.data.DTO.response.ResponseUserInfoDto
import com.sopt.now.data.Key.USER
import com.sopt.now.data.service.ServicePool
import com.sopt.now.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private val authService by lazy { ServicePool.authService }
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { }

    private val homeViewModel = HomeViewModel()
    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUserData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUserData() {
        activity?.intent?.apply {
            getUserInfo(getStringExtra("userId") ?: "0")
        }
    }

    private fun initFriendRecyclerView() {
        val userAdapter = UserAdapter().apply { user?.let { setUserList(it) } }
        val friendAdapter = FriendAdapter().apply { setFriendList(homeViewModel.getFriendInfo()) }

        binding.rvHomeFriends.run {
            adapter = ConcatAdapter(userAdapter, friendAdapter)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun getUserInfo(userId: String) {
        authService.getUserInfo(userId).enqueue(object : Callback<ResponseUserInfoDto> {
            override fun onResponse(
                call: Call<ResponseUserInfoDto>,
                response: Response<ResponseUserInfoDto>
            ) {
                if(response.isSuccessful) {
                    val data : ResponseUserInfoDto? = response.body()
                    user = User(
                        R.drawable.ic_person_white_24,
                        data?.data?.authenticationId ?: "id",
                        data?.data?.nickname ?: "닉네임",
                        data?.data?.phone ?: "phone"
                    )
                    initFriendRecyclerView()
                } else {
                    val error = response.message()
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseUserInfoDto>, t: Throwable) {
                Toast.makeText(context, "서버 통신 에러", Toast.LENGTH_SHORT).show()
            }
        })
    }
}