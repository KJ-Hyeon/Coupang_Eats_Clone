package com.jeong.android.coupang_eatsclone.src.main.join

import android.os.Bundle
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityJoinBinding
import com.jeong.android.coupang_eatsclone.databinding.ActivityLoginBinding
import com.jeong.android.coupang_eatsclone.src.main.join.models.PostSignUpRequest
import com.jeong.android.coupang_eatsclone.src.main.join.models.SignUpResponse

class JoinActivity: BaseActivity<ActivityJoinBinding>(ActivityJoinBinding::inflate),
        JoinActivityInterface{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnJoin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val name = binding.etName.text.toString()
            val phone = binding.etPhone.text.toString()
            val postRequest = PostSignUpRequest(email,password, name, phone)
            showLoadingDialog(this)
            JoinService(this).tryPostSignUp(postRequest)
        }
    }

    override fun onPostSignUpSuccess(response: SignUpResponse) {
        dismissLoadingDialog()
        response.message?.let { showCustomToast(it) }
    }

    override fun onPostSignUpFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류: $message")
    }
}
