package com.sopt.now.Mypage

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sopt.now.User.User
import com.sopt.now.data.Key.USER
import com.sopt.now.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding) { }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTextViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initTextViews() {
        activity?.intent?.apply {
            with(binding) {
                val user = if (Build.VERSION.SDK_INT >= 33) {
                    getParcelableExtra(USER, User::class.java)
                } else {
                    getParcelableExtra(USER)
                }
                tvMypageNickname.setText(user?.nickname)
                tvMypageIdContent.setText(user?.id)
                tvMypagePhoneContent.setText(user?.phone)
            }
        }
    }
}