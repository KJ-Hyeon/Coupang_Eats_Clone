package com.jeong.android.coupang_eatsclone.src.main.home

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.config.BaseFragment
import com.jeong.android.coupang_eatsclone.databinding.FragmentHomeBinding
import com.jeong.android.coupang_eatsclone.src.main.Store.StoreActivity
import com.jeong.android.coupang_eatsclone.src.main.adress.MapSettingActivity
import com.jeong.android.coupang_eatsclone.src.main.cart.CartActivity
import com.jeong.android.coupang_eatsclone.src.main.cart.models.CartResponse
import com.jeong.android.coupang_eatsclone.src.main.home.models.HomeStore
import com.jeong.android.coupang_eatsclone.src.main.home.models.StoreRes
import com.jeong.android.coupang_eatsclone.src.main.page.models.Ad

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
    HomeFragmentInterface {

    private var myHandler = MyHandler()
    private val data = ArrayList<Int>()
    private var currentPos = 0
    private lateinit var homeRecyclerViewAdapter: HomeRecyclerViewAdapter
    private var storeList = mutableListOf<StoreRes>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        HomeService(this).tryGetStore()
        HomeService(this).tryGetCart()

        homeRecyclerViewAdapter = HomeRecyclerViewAdapter(storeList)
        binding?.revHome?.adapter = homeRecyclerViewAdapter



        homeRecyclerViewAdapter.setOnItemClickListener(object : HomeRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(v: View, Pos: Int, item: StoreRes) {
                val intent = Intent(requireContext(), StoreActivity::class.java)
                intent.putExtra("storeId",item.store_id)
                startActivity(intent)
            }
        })

        // 임의 데이터
        initList()
        val viewpager = AdViewPagerAdapter(data)
        binding?.adBanner?.adapter = viewpager
        binding?.adBanner?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        autoScrollStart()

        binding?.LinAddress?.setOnClickListener {
            val intent = Intent(requireContext(), MapSettingActivity::class.java)
            startActivity(intent)
        }

        binding?.adBanner?.apply {
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
        binding?.cartBar?.setOnClickListener {
            val intent = Intent(requireContext(), CartActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initList() {
        data.add(R.drawable.bg_banner1)
        data.add(R.drawable.bg_banner2)
        data.add(R.drawable.bg_banner3)
        data.add(R.drawable.bg_banner4)
        data.add(R.drawable.bg_banner5)
    }

    override fun onGetStoreSuccess(response: HomeStore) {

        if (response.result.storeResList.isNotEmpty()) {
            storeList = response.result.storeResList.toMutableList()
            homeRecyclerViewAdapter.addData(storeList)
        }
    }

    override fun onGetStoreFailure(message: String) {
        Log.e(TAG, "onGetStoreFailure: $message", )
    }

    override fun onGetCartSuccess(response: CartResponse) {
        binding?.cartBar?.visibility = View.VISIBLE
        /**
         * 카트에 아무것도 없을때와 카트에 물건이 차 있는 경우를 구분하기 위한 방법?
         * 카트가 비어있을때 response가 어떻게 오는지 봐야할듯...
         */
    }

    override fun onGetCartFailure(message: String) {
        Log.e(TAG, "onGetStoreFailure: $message", )
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
                binding?.adBanner?.setCurrentItem(++currentPos, true)
                autoScrollStart() // 계속해서 스크롤을 해야하기 때문에
            }
        }
    }


    override fun onPause() {
        super.onPause()
        myHandler.removeMessages(0)
    }

    override fun onResume() {
        super.onResume()
        val mainAddress = ApplicationClass.sSharedPreferences.getString("mainAddress","주소를 설정해주세요")
        binding?.tvAdress?.text = mainAddress
    }
}