package com.jeong.android.coupang_eatsclone.src.main.page

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.BaseFragment
import com.jeong.android.coupang_eatsclone.databinding.FragmentHomeBinding
import com.jeong.android.coupang_eatsclone.databinding.FragmentPageBinding
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressListResponse
import com.jeong.android.coupang_eatsclone.src.main.home.AdViewPagerAdapter
import com.jeong.android.coupang_eatsclone.src.main.page.models.Ad
import com.jeong.android.coupang_eatsclone.src.main.page.models.DetailAddressResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.UserResponse

class PageFragment : BaseFragment<FragmentPageBinding>(FragmentPageBinding::bind, R.layout.fragment_page) ,
        PageFragmentInterface{

    private var myHandler = MyHandler()
    private var currentPos = 0
    private val data = mutableListOf<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PageFragmentService(this).tryGetUser()

        initList()
        val viewpager = PageAdViewPagerAdapter(data)
        binding?.pageAdBanner?.adapter = viewpager
        binding?.pageAdBanner?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        autoScrollStart()

        binding?.linAddressManager?.setOnClickListener {
            val intent = Intent(requireContext(),AddressManagerActivity::class.java)
            startActivity(intent)
        }
        binding?.tvSetting?.setOnClickListener {
            val intent = Intent(requireContext(), SettingActivity::class.java)
            startActivity(intent)
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.commit()
        }

        binding?.pageAdBanner?.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    currentPos = position
                    binding?.tvCurrentBanner?.text = "${(position%data.size)+1}"
                    binding?.tvTotalBanner?.text = data.size.toString()
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    when(state) {
                        ViewPager2.SCROLL_STATE_IDLE -> autoScrollStart()
                        ViewPager2.SCROLL_STATE_DRAGGING -> autoScrollStop()
                    }
                }
            })
        }

    }
    private fun autoScrollStart() {
        myHandler.removeMessages(0) // handler를 스탑해야지 계속해서 생성되는것을 막을 수 있음
        myHandler.sendEmptyMessageDelayed(0, 2000) // 2초후에 메시지를 보냄?
    }

    private fun autoScrollStop(){
        myHandler.removeMessages(0) // handler를 스탑
    }

    private inner class MyHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if(msg.what == 0) {
                binding?.pageAdBanner?.setCurrentItem(++currentPos, true)
                autoScrollStart() // 계속해서 스크롤을 해야하기 때문에
            }
        }
    }

    override fun onGetuserSuccess(response: UserResponse) {
        val userName = response.result.user_name
        var userNameFirst = userName.substring(0 until 1)
        for (i in userName.indices-1) {
            userNameFirst += "*"
        }
        binding?.tvUserName?.text = userNameFirst
        val phoneNumber = response.result.user_phone
        binding?.tvPhoneFront?.text = phoneNumber.substring(0 until 3)
        binding?.tvPhoneEnd?.text = phoneNumber.subSequence(7 until 11)


    }

    private fun initList() {
        data.add(R.drawable.bg_banner1)
        data.add(R.drawable.bg_banner2)
        data.add(R.drawable.bg_banner3)
        data.add(R.drawable.bg_banner4)
        data.add(R.drawable.bg_banner5)
    }

    override fun onGetuserFailure(message: String) {
        showCustomToast(message)
    }

    // 리사이클러뷰
    override fun onPause() {
        super.onPause()
        myHandler.removeMessages(0)
    }

}