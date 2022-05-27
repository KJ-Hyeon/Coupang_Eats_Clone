package com.jeong.android.coupang_eatsclone.src.main.join

import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.CheckBox
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityJoinBinding
import com.jeong.android.coupang_eatsclone.databinding.ActivityLoginBinding
import com.jeong.android.coupang_eatsclone.src.main.join.models.PostSignUpRequest
import com.jeong.android.coupang_eatsclone.src.main.join.models.SignUpResponse
import java.lang.reflect.Type

class JoinActivity : BaseActivity<ActivityJoinBinding>(ActivityJoinBinding::inflate),
    JoinActivityInterface {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val all_check_list = arrayListOf<CheckBox>(
            binding.joinCheck1,
            binding.joinCheck2,
            binding.joinCheck3,
            binding.joinCheck4,
            binding.joinCheck5,
            binding.joinCheck6,
            binding.joinCheck7,
            binding.joinCheck8,
            binding.joinCheck9,
            binding.joinCheck10
        )

        val essetial_check_list = arrayListOf<CheckBox>(
            binding.joinCheck1, binding.joinCheck2, binding.joinCheck3, binding.joinCheck4,
            binding.joinCheck5
        )

        binding.btnJoin.setOnClickListener {
            if (essetial_check_list[0].isChecked && essetial_check_list[1].isChecked && essetial_check_list[2].isChecked &&
                essetial_check_list[3].isChecked && essetial_check_list[4].isChecked) {
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()
                val name = binding.etName.text.toString()
                val phone = binding.etPhone.text.toString()
                val postRequest = PostSignUpRequest(email, password, name, phone)
                showLoadingDialog(this)
                JoinService(this).tryPostSignUp(postRequest)
            }
            else {
                showCustomToast("필수항목에 동의해주세요")
            }
        }

        binding.icVisible.setOnClickListener {
            TogglePassword()
        }

        binding.joinCheckAll.setOnClickListener {
            if (binding.joinCheckAll.isChecked) {
                for (checkbox in all_check_list) {
                    checkbox.isChecked = true
                }
            }else {
                for (checkbox in all_check_list) {
                    checkbox.isChecked = false
                }
            }
        }
        binding.joinCheck7.setOnClickListener {
            if (binding.joinCheck7.isChecked) {
                binding.joinCheck6.isChecked
                binding.joinCheck8.isChecked =  true
                binding.joinCheck9.isChecked =  true
                binding.joinCheck10.isChecked =  true
            }else {
                binding.joinCheck8.isChecked =  false
                binding.joinCheck9.isChecked =  false
                binding.joinCheck10.isChecked =  false
            }
        }
        binding.joinCheck6.setOnClickListener {
            if (binding.joinCheck6.isChecked) {
                binding.joinCheck7.isChecked
                binding.joinCheck8.isChecked =  true
                binding.joinCheck9.isChecked =  true
                binding.joinCheck10.isChecked =  true
            }else {
                binding.joinCheck7.isChecked =  false
                binding.joinCheck8.isChecked =  false
                binding.joinCheck9.isChecked =  false
                binding.joinCheck10.isChecked =  false
            }
        }
        binding.joinCheck8.setOnClickListener {
            if (binding.joinCheck8.isChecked) {
                binding.joinCheck6.isChecked = true
                binding.joinCheck7.isChecked = true
            }
        }
        binding.joinCheck9.setOnClickListener {
            if (binding.joinCheck8.isChecked) {
                binding.joinCheck6.isChecked = true
                binding.joinCheck7.isChecked = true
            }
        }
        binding.joinCheck10.setOnClickListener {
            if (binding.joinCheck8.isChecked) {
                binding.joinCheck6.isChecked = true
                binding.joinCheck7.isChecked = true
            }
        }
        if (!binding.joinCheck8.isChecked && !binding.joinCheck9.isChecked && !binding.joinCheck10.isChecked) {
            binding.joinCheck7.isChecked = false
        }

        binding.icFinish.setOnClickListener {
            finish()
        }
    }

    private fun TogglePassword() {
        if (binding.etPassword.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
            binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.icVisible.setImageResource(R.drawable.ic_join_pw_invisible)
        } else {
            binding.etPassword.transformationMethod =HideReturnsTransformationMethod.getInstance()
            binding.icVisible.setImageResource(R.drawable.ic_join_pw_visible)
        }
        binding.etPassword.setSelection(binding.etPassword.text.length)
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
