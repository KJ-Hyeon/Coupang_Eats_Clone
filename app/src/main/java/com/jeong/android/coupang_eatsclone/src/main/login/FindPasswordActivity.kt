package com.jeong.android.coupang_eatsclone.src.main.login

import android.os.Bundle
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityFindPasswordBinding
import com.jeong.android.coupang_eatsclone.src.main.login.models.FindPasswordResponse

class FindPasswordActivity : BaseActivity<ActivityFindPasswordBinding>(ActivityFindPasswordBinding::inflate) ,
        FindPasswordActivityInterface{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnPhone.setOnClickListener {
            val userName = binding.etName.text.toString()
            val userEmail = binding.etEmail.text.toString()
            if (userName.isEmpty() || userEmail.isEmpty()) {
                showCustomToast("이름과 이메일을 입력해주세요")
            } else {
                FindPasswordService(this).tryGetPassword(userName, userEmail)
                showLoadingDialog(this)
            }
        }

    }

    override fun onGetPasswordSuccess(response: FindPasswordResponse) {
        dismissLoadingDialog()
        //showCustomToast(response.resultFindPassword.user_password)
    }

    override fun onGetPasswordFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}