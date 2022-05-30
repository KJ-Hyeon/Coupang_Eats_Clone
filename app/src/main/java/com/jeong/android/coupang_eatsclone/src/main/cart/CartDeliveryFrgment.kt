package com.jeong.android.coupang_eatsclone.src.main.cart

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.ApplicationClass.Companion.sSharedPreferences
import com.jeong.android.coupang_eatsclone.config.BaseFragment
import com.jeong.android.coupang_eatsclone.databinding.FrgmentCartDeliveryBinding

class CartDeliveryFrgment :BaseFragment<FrgmentCartDeliveryBinding>(FrgmentCartDeliveryBinding::bind, R.layout.frgment_cart_delivery) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainAddress = sSharedPreferences.getString("mainAddress",null)
        val roadAddress = sSharedPreferences.getString("RoadAddress",null)
        binding?.tvCartMainAddress?.text = mainAddress
        binding?.tvCartRoadAddress?.text = roadAddress

        binding?.btnAmend?.setOnClickListener {
            val amountDialog = CustomAmountDialog(requireContext())
            amountDialog.showDialog()
        }
    }
}