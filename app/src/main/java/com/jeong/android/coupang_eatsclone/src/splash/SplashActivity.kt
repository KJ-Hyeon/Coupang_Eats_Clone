package com.jeong.android.coupang_eatsclone.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.jeong.android.coupang_eatsclone.MainActivity
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivitySplashBinding

class SplashActivity: BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val first = ApplicationClass.sSharedPreferences.getBoolean("First",false)
        if (first == false){
            val edit = ApplicationClass.sSharedPreferences.edit()
            edit.putBoolean("First", true)
            edit.commit()
            val intent = Intent(this,FirstIntroActivity::class.java)
            startActivity(intent)
        }else {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 1500)
        }
    }
}