package com.jeong.android.coupang_eatsclone.src.main.adress

import android.content.Intent
import android.os.Bundle
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityLoginBinding
import com.jeong.android.coupang_eatsclone.databinding.ActivityMainBinding
import com.jeong.android.coupang_eatsclone.databinding.ActivityMapSettingBinding

class MapSettingActivity: BaseActivity<ActivityMapSettingBinding>(ActivityMapSettingBinding::inflate){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnCurrentLocation.setOnClickListener {
            val intent = Intent(this,CurrentUserMapActivity::class.java)
            startActivity(intent)
        }
    }
}