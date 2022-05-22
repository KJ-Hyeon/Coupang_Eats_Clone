package com.jeong.android.coupang_eatsclone.src.main.login

import android.os.Bundle
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityLoginBinding
import com.jeong.android.coupang_eatsclone.src.main.login.models.LoginResponse
import com.jeong.android.coupang_eatsclone.src.main.login.models.PostLoginRequest

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate),
        LoginActivityInterface{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val loginRequest = PostLoginRequest(email, password)
            LoginService(this).tryPostLogin(loginRequest)
            showLoadingDialog(this)
        }
    }

    override fun onPostLoginSuccess(response: LoginResponse) {
        dismissLoadingDialog()
        response.message?.let { showCustomToast(it) }
    }

    override fun onPostLoginFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류: $message")
    }
}