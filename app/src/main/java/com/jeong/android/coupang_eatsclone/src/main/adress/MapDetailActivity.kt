package com.jeong.android.coupang_eatsclone.src.main.adress

import android.content.Intent
import android.os.Bundle
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityMapDetailBinding

class MapDetailActivity : BaseActivity<ActivityMapDetailBinding>(ActivityMapDetailBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent()
        intent.getStringExtra("Adress")
        intent.getStringExtra("RoadAdress")
    }
}