package com.sopt.now.presentation.Home

import android.icu.util.Freezable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.presentation.Home.Friend.Friend
import com.sopt.now.presentation.Home.Friend.FriendAdapter
import com.sopt.now.R
import com.sopt.now.presentation.Home.User.User
import com.sopt.now.presentation.Home.User.UserAdapter
import com.sopt.now.data.model.response.ResponseUserInfoDto
import com.sopt.now.data.model.response.ResponseUserListDto
import com.sopt.now.data.ServicePool
import com.sopt.now.databinding.FragmentHomeBinding
import com.sopt.now.presentation.Key.USERID
import com.sopt.now.presentation.common.ViewModelFactory
import com.sopt.now.util.UiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private val userService by lazy { ServicePool.userService }
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { }

    private val homeViewModel: HomeViewModel by viewModels { ViewModelFactory() }
    private var friendList: List<Friend> ?= null

    private lateinit var userAdapter: UserAdapter
    private lateinit var friendAdapter: FriendAdapter

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

        homeViewModel.getUserInfo(activity?.intent?.getStringExtra(USERID) ?: "0")
        initAdapter()
        collectUserInfo()
//        getUserList()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        userAdapter = UserAdapter()
        friendAdapter = FriendAdapter()

        binding.rvHomeFriends.adapter = ConcatAdapter(userAdapter, friendAdapter)
    }

    private fun collectUserInfo() {
        homeViewModel.homeUserState.flowWithLifecycle(lifecycle).onEach { homeUserState ->
            when(homeUserState) {
                is UiState.Success -> {
                    userAdapter.setUserList(homeUserState.data)
                }
                is UiState.Error -> showToastMessage(homeUserState.message)
                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun showToastMessage(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}