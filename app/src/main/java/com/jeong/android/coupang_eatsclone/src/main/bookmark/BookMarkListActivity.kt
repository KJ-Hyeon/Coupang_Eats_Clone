package com.jeong.android.coupang_eatsclone.src.main.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.config.BaseFragment
import com.jeong.android.coupang_eatsclone.databinding.ActivityBookmarkListBinding
import com.jeong.android.coupang_eatsclone.src.main.Store.StoreActivity
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.BookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.ResultBookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.ReviewSimple
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.StoreSimple
import com.jeong.android.coupang_eatsclone.src.main.home.HomeRecyclerViewAdapter
import com.jeong.android.coupang_eatsclone.src.main.home.models.Result

class BookMarkListActivity : BaseActivity<ActivityBookmarkListBinding>(ActivityBookmarkListBinding::inflate),
            BookMarkInterface{

    private lateinit var bookmarkRecyclerViewAdapter: BookMarkRecyclerViewAdapter
    private var storeList = mutableListOf<StoreSimple>()
    private var reviewList = mutableListOf<ReviewSimple>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BookMarkService(this).tryGetBookmark()

        bookmarkRecyclerViewAdapter = BookMarkRecyclerViewAdapter()
        binding.revBookmark.adapter = bookmarkRecyclerViewAdapter

        bookmarkRecyclerViewAdapter.setOnItemClickListener(object : BookMarkRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(v: View, Pos: Int) {
                val intent = Intent(this@BookMarkListActivity, StoreActivity::class.java)
                intent.putExtra("Pos",Pos+1)
                startActivity(intent)
            }
        })

    }

    override fun onGetBookMarkSuccess(response: BookMarkResponse) {
        binding.tvBookmarkCnt.text = "총 ${response.result.bookmark_count}개"
        storeList = response.result.storeSimpleList.toMutableList()
        reviewList = response.result.reviewSimpleList.toMutableList()
        bookmarkRecyclerViewAdapter.addData(storeList, reviewList)

    }

    override fun onGetBookMarkFailure(message: String) {
        showCustomToast("오류: $message")
    }
}