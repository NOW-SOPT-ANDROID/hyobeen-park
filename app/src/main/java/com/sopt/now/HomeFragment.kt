package com.sopt.now

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
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
            user = getStringExtra(LoginActivity.NICKNAME)?.let {
                getStringExtra(LoginActivity.MBTI)?.let { it1 ->
                    User(
                        R.drawable.ic_person_white_24,
                        it,
                        it1
                    )
                }
            }
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