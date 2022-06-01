package com.jeong.android.coupang_eatsclone.src.main.search

import android.content.Intent
import android.os.Bundle
import android.system.Os.bind
import android.view.View
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.BaseFragment
import com.jeong.android.coupang_eatsclone.databinding.FragmentHomeBinding
import com.jeong.android.coupang_eatsclone.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::bind, R.layout.fragment_search){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.searchEdit?.setOnClickListener {
            val intent = Intent(requireContext(), SearchKeywordActivity::class.java)
            startActivity(intent)
        }
    }
}