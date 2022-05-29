package com.jeong.android.coupang_eatsclone.src.main.order

import android.os.Bundle
import android.view.View
import com.google.android.material.appbar.AppBarLayout
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityOrderBinding
import kotlin.math.abs

class OrderActivity : BaseActivity<ActivityOrderBinding>(ActivityOrderBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val pos = intent.getIntExtra("Pos", -1)
        showCustomToast("$pos")

        appbarView()

    }

    private fun appbarView() {
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener{ appBarLayout, verticalOffset ->
            if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                //접혔을때
                binding.tvAppBarMenuName.visibility = View.VISIBLE
                binding.icBack.setImageResource(R.drawable.ic_arrow_back_black)
                binding.icShare.setImageResource(R.drawable.ic_share_black)
            } else {
                //펴졌을때
                binding.tvAppBarMenuName.visibility = View.GONE
                binding.icBack.setImageResource(R.drawable.ic_arrow_back_white)
                binding.icShare.setImageResource(R.drawable.ic_share_white)
            }
        })
    }
}