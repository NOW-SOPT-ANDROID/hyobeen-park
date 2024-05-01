package com.sopt.now.Mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.Login.LoginActivity.Companion.ID
import com.sopt.now.Login.LoginActivity.Companion.MBTI
import com.sopt.now.Login.LoginActivity.Companion.NICKNAME

class MypageFragment : Fragment() {
    private var _binding : FragmentMypageBinding? = null
    private val binding : FragmentMypageBinding
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
                tvMypageNickname.setText(getStringExtra(NICKNAME))
                tvMypageIdContent.setText(getStringExtra(ID))
                tvMypageMbti.setText(getStringExtra(MBTI))
            }
        }
    }
}