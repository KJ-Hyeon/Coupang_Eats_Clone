package com.jeong.android.coupang_eatsclone.src.main.login

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.config.ApplicationClass.Companion.sSharedPreferences
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
        binding.icVisible.setOnClickListener {
            TogglePassword()
        }
    }

    override fun onPostLoginSuccess(response: LoginResponse) {
        dismissLoadingDialog()
        response.message?.let { showCustomToast(it) }
        val jwt = response.result.jwt
        val userId = response.result.userId
        ApplicationClass.X_ACCESS_TOKEN
        val edit = sSharedPreferences.edit()
        edit.putString("X_ACCESS_TOKEN",jwt)
        edit.putInt("USER_ID",userId)
        finish()
    }

    override fun onPostLoginFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류: $message")
    }

    private fun TogglePassword() {
        if (binding.etPassword.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
            binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.icVisible.setImageResource(R.drawable.ic_login_pw_invisible)
        } else {
            binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.icVisible.setImageResource(R.drawable.ic_login_pw_visible)
        }
        binding.etPassword.setSelection(binding.etPassword.text.length)
    }
}