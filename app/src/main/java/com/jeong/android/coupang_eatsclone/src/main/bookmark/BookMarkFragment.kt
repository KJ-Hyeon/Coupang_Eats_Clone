package com.jeong.android.coupang_eatsclone.src.main.bookmark

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.BaseFragment
import com.jeong.android.coupang_eatsclone.databinding.FragmentBookmarkBinding
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.BookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.home.HomeFragment
import kotlin.math.log


class BookMarkFragment : BaseFragment<FragmentBookmarkBinding>(FragmentBookmarkBinding::bind, R.layout.fragment_bookmark),
            BookMarkInterface{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        BookMarkService(this).tryGetBookmark()
    }
    fun changeListView() {
        val intent = Intent(requireContext(), BookMarkListActivity::class.java)
        startActivity(intent)
    }
    fun changeNoneView() {
        val intent = Intent(requireContext(), BookMarkNoneActivity::class.java)
        startActivity(intent)
    }

    override fun onGetBookMarkSuccess(response: BookMarkResponse) {
        if (response.result.bookmark_count == 0) {
            changeNoneView()
        } else {
            changeListView()
        }
    }

    override fun onGetBookMarkFailure(message: String) {
        showCustomToast("오류 $message")
    }
}