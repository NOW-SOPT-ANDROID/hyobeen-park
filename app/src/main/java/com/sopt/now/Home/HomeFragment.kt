package com.sopt.now.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.Friend.FriendAdapter
import com.sopt.now.Login.LoginActivity
import com.sopt.now.R
import com.sopt.now.User.User
import com.sopt.now.User.UserAdapter
import com.sopt.now.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding : FragmentHomeBinding
        get() = requireNotNull(_binding) { }

    private val homeViewModel = HomeViewModel()
    private var user : User? = null

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
        initFriendRecyclerView()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUserData() {
        activity?.intent?.apply {
            user = User(
                R.drawable.ic_person_white_24,
                getStringExtra(LoginActivity.NICKNAME) ?: "닉네임",
                getStringExtra(LoginActivity.MBTI) ?: "MBTI"
            )
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
}