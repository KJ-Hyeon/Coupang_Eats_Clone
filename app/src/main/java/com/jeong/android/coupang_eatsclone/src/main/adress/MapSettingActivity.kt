package com.jeong.android.coupang_eatsclone.src.main.adress

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.jeong.android.coupang_eatsclone.config.ApplicationClass.Companion.sSharedPreferences
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityLoginBinding
import com.jeong.android.coupang_eatsclone.databinding.ActivityMainBinding
import com.jeong.android.coupang_eatsclone.databinding.ActivityMapSettingBinding
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressListResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.ResultAddressList
import com.jeong.android.coupang_eatsclone.src.main.home.HomeRecyclerViewAdapter

class MapSettingActivity: BaseActivity<ActivityMapSettingBinding>(ActivityMapSettingBinding::inflate) ,
            MapSettingInterface{

    private lateinit var addressRecyclerViewAdapter: AddressRecyclerViewAdapter
    private var addressList = mutableListOf<ResultAddressList>()
    val editor = sSharedPreferences.edit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapSettingService(this).tryGetAddressList()

        addressRecyclerViewAdapter = AddressRecyclerViewAdapter(addressList)
        binding.addressRev.adapter = addressRecyclerViewAdapter

        addressRecyclerViewAdapter.setOnItemClickListener(object : AddressRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: ResultAddressList, Pos: Int) {
                editor.putString("mainAddress",data.main_address)
                editor.putInt("AddressId",data.address_id)
                editor.commit()
                finish()
            }
        })


        binding.btnCurrentLocation.setOnClickListener {
            val intent = Intent(this,CurrentUserMapActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.icFinish.setOnClickListener {
            finish()
        }
    }

    override fun onGetAddressSuccess(response: AddressListResponse) {
        if (response.result.isNotEmpty()) {
            addressList = response.result.toMutableList()
            addressRecyclerViewAdapter.addData(addressList)
        }
    }

    override fun onGetAddressFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}