package com.sopt.now.presentation.Mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sopt.now.data.model.response.ResponseUserInfoDto
import com.sopt.now.data.ServicePool
import com.sopt.now.databinding.FragmentMypageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageFragment : Fragment() {
    private val authService by lazy { ServicePool.authService }
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
        getUserInfo(getUserId())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initTextViews(id: String, nickname: String, phone: String) {
        with(binding) {
            tvMypageNickname.setText(nickname)
            tvMypageIdContent.setText(id)
            tvMypagePhoneContent.setText(phone)
        }
    }

    private fun getUserId(): String {
        return activity?.intent?.getStringExtra("userId") ?: "0"
    }

    private fun getUserInfo(userId: String) {
        authService.getUserInfo(userId).enqueue(object : Callback<ResponseUserInfoDto> {
            override fun onResponse(
                call: Call<ResponseUserInfoDto>,
                response: Response<ResponseUserInfoDto>
            ) {
                if (response.isSuccessful) {
                    val data: ResponseUserInfoDto? = response.body()
                    data?.data?.apply {
                        initTextViews(authenticationId, nickname, phone)
                    }
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