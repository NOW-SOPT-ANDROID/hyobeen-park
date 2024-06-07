package com.sopt.now.presentation.Mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.domain.model.User
import com.sopt.now.presentation.common.ViewModelFactory
import com.sopt.now.util.UiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MypageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding) { }

    private val mypageViewModel: MypageViewModel by viewModels { ViewModelFactory() }

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

        mypageViewModel.getUserInfo(getUserId())
        collectUserInfo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initTextViews(user: User) {
        with(binding) {
            tvMypageNickname.setText(user.nickname)
            tvMypageIdContent.setText(user.id)
            tvMypagePhoneContent.setText(user.phone)
        }
    }

    private fun getUserId(): String {
        return activity?.intent?.getStringExtra("userId") ?: "0"
    }

    private fun collectUserInfo() {
        mypageViewModel.mypageState.flowWithLifecycle(lifecycle).onEach { mypageState ->
            when (mypageState) {
                is UiState.Success -> {
                    initTextViews(mypageState.data)
                }

                is UiState.Error -> showToastMessage(mypageState.message)
                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun showToastMessage(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}