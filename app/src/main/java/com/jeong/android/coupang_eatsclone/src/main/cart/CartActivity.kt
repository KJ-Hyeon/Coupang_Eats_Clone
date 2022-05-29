package com.jeong.android.coupang_eatsclone.src.main.cart

import android.os.Bundle
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityCartBinding

class CartActivity : BaseActivity<ActivityCartBinding>(ActivityCartBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tablayout = binding.cartTab
        tablayout.getTabAt(0)!!.text = "배달시간"

        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_cart, CartDeliveryFrgment())
            .commit()
    }
}