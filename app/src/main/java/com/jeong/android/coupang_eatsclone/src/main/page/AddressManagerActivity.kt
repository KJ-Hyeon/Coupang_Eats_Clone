package com.jeong.android.coupang_eatsclone.src.main.page

import android.content.Intent
import android.os.Bundle
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityAddressManagerBinding
import com.jeong.android.coupang_eatsclone.src.main.adress.MapDetailActivity
import com.jeong.android.coupang_eatsclone.src.main.adress.MapSettingActivity

class AddressManagerActivity : BaseActivity<ActivityAddressManagerBinding>(ActivityAddressManagerBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvAddAddress.setOnClickListener {
            val intent = Intent(this, MapDetailActivity::class.java)
            intent.putExtra("checkDelete", true)
            startActivity(intent)
        }
    }
}