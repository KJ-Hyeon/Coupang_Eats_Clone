package com.jeong.android.coupang_eatsclone.src.splash

import android.content.Intent
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.jeong.android.coupang_eatsclone.MainActivity
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityFirstIntroBinding
import com.jeong.android.coupang_eatsclone.src.main.home.AdViewPagerAdapter

class FirstIntroActivity : BaseActivity<ActivityFirstIntroBinding>(ActivityFirstIntroBinding::inflate) {

    private val data = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        data.add(R.drawable.intro_first)
        data.add(R.drawable.intro_second)
        data.add(R.drawable.intro_three)

        val viewpager = IntroViewpagerAdapter(data)
        binding.pagerIntro.adapter = viewpager
        binding.pagerIntro.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.springDotsIndicator.setViewPager2(binding.pagerIntro)

        binding.btnStart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}