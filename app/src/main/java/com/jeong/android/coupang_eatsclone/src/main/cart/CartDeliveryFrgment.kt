package com.jeong.android.coupang_eatsclone.src.main.cart

import android.os.Bundle
import android.view.View
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.ApplicationClass.Companion.sSharedPreferences
import com.jeong.android.coupang_eatsclone.config.BaseFragment
import com.jeong.android.coupang_eatsclone.databinding.FrgmentCartDeliveryBinding

class CartDeliveryFrgment :BaseFragment<FrgmentCartDeliveryBinding>(FrgmentCartDeliveryBinding::bind, R.layout.frgment_cart_delivery) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //mainAddress
        //RoadAddress
        val mainAddress = sSharedPreferences.getString("mainAddress",null)
        val roadAddress = sSharedPreferences.getString("RoadAddress",null)
        binding?.tvCartMainAddress?.text = mainAddress
        binding?.tvCartRoadAddress?.text = roadAddress
    }
}