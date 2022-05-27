package com.jeong.android.coupang_eatsclone.src.main.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.BaseFragment
import com.jeong.android.coupang_eatsclone.databinding.FragmentBookmarkBinding
import com.jeong.android.coupang_eatsclone.src.main.home.HomeFragment


class BookMarkFragment : BaseFragment<FragmentBookmarkBinding>(FragmentBookmarkBinding::bind, R.layout.fragment_bookmark) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = ArrayList<String>()
        if (data.isEmpty()) {
            changeNoneView()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fl_main, HomeFragment())
                ?.commit()

        }else {
            changeListView()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fl_main, HomeFragment())
                ?.commit()
        }

    }
    fun changeListView() {
        val intent = Intent(requireContext(), BookMarkListActivity::class.java)
        startActivity(intent)
    }
    fun changeNoneView() {
        val intent = Intent(requireContext(), BookMarkNoneActivity::class.java)
        startActivity(intent)
    }
}