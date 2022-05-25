package com.jeong.android.coupang_eatsclone.src.main.adress

import android.content.Intent
import android.os.Bundle
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityLoginBinding
import com.jeong.android.coupang_eatsclone.databinding.ActivityMainBinding
import com.jeong.android.coupang_eatsclone.databinding.ActivityMapSettingBinding
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressListResponse
import com.jeong.android.coupang_eatsclone.src.main.home.HomeRecyclerViewAdapter

class MapSettingActivity: BaseActivity<ActivityMapSettingBinding>(ActivityMapSettingBinding::inflate) ,
            MapSettingInterface{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapSettingService(this).tryGetAddressList()


        binding.btnCurrentLocation.setOnClickListener {
            val intent = Intent(this,CurrentUserMapActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onGetAddressSuccess(response: AddressListResponse) {
        if (response.result.isNotEmpty()) {
            val addressList = response.result
            val addressRecyclerViewAdapter = AddressRecyclerViewAdapter(addressList)
            binding.addressRev.adapter = addressRecyclerViewAdapter
        }
    }

    override fun onGetAddressFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}