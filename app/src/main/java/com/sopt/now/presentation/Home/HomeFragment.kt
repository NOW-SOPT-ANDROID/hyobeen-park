package com.sopt.now.presentation.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.presentation.Home.Friend.Friend
import com.sopt.now.presentation.Home.Friend.FriendAdapter
import com.sopt.now.R
import com.sopt.now.presentation.Home.User.User
import com.sopt.now.presentation.Home.User.UserAdapter
import com.sopt.now.data.DTO.response.ResponseUserInfoDto
import com.sopt.now.data.DTO.response.ResponseUserListDto
import com.sopt.now.data.ServicePool
import com.sopt.now.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private val authService by lazy { ServicePool.authService }
    private val userService by lazy { ServicePool.userService }
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { }

    private var user: User? = null
    private var friendList: List<Friend> ?= null

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
        getUserList()

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
        val friendAdapter = FriendAdapter().apply { friendList?.let { setFriendList(it) } }


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
                Toast.makeText(context, R.string.server_connection_error, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getUserList() {
        userService.getUserList(2).enqueue(object : Callback<ResponseUserListDto> {
            override fun onResponse(
                call: Call<ResponseUserListDto>,
                response: Response<ResponseUserListDto>
            ) {
                if(response.isSuccessful) {
                    val data : ResponseUserListDto? = response.body()
                    friendList = data?.data
                    initFriendRecyclerView()
                } else {
                    val error = response.message()
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseUserListDto>, t: Throwable) {
                Toast.makeText(context, R.string.server_connection_error, Toast.LENGTH_SHORT).show()
            }
        })
    }

}