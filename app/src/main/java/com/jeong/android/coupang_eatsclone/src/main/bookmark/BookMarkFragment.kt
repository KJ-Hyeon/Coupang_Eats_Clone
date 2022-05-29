package com.jeong.android.coupang_eatsclone.src.main.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.BaseFragment
import com.jeong.android.coupang_eatsclone.databinding.FragmentBookmarkBinding
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.BookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.home.HomeFragment


class BookMarkFragment : BaseFragment<FragmentBookmarkBinding>(FragmentBookmarkBinding::bind, R.layout.fragment_bookmark),
            BookMarkInterface{

    val data = mutableListOf<BookMarkResponse>()

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
        data.add(response)
        if (data.isEmpty()) {
            changeNoneView()
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.fl_main, HomeFragment())
//                ?.commit()

        }else {
            changeListView()
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.fl_main, HomeFragment())
//                ?.commit()
        }
    }

    override fun onGetBookMarkFailure(message: String) {
        showCustomToast("오류 $message")
    }
}