package com.jeong.android.coupang_eatsclone.src.main.order_list

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.BaseFragment
import com.jeong.android.coupang_eatsclone.databinding.FragmentOrderListBinding
import com.jeong.android.coupang_eatsclone.src.main.bookmark.BookMarkListActivity
import com.jeong.android.coupang_eatsclone.src.main.bookmark.BookMarkNoneActivity

class OrderListFragment : BaseFragment<FragmentOrderListBinding>(FragmentOrderListBinding::bind, R.layout.fragment_order_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = ArrayList<String>()

        if (data.isEmpty()) {
            changeNoneView()
        } else {
            changeListView()
        }
        binding?.orderListTab?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 0 ) {
                    if (data.isEmpty()) {
                        changeNoneView()
                    } else {
                        changeListView()
                    }
                } else {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.fl_order_list, OrderListReadyFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    fun changeListView() {
        childFragmentManager.beginTransaction()
            .replace(R.id.fl_order_list, OrderListRevFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
    fun changeNoneView() {
        childFragmentManager.beginTransaction()
            .replace(R.id.fl_order_list, OrderListNoneFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

}