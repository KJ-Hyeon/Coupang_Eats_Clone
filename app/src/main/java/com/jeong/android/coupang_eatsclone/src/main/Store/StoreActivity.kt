package com.jeong.android.coupang_eatsclone.src.main.Store

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityStoreBinding
import com.jeong.android.coupang_eatsclone.src.main.Store.models.MenuCategory
import com.jeong.android.coupang_eatsclone.src.main.Store.models.Review
import com.jeong.android.coupang_eatsclone.src.main.Store.models.StoreResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.BookMarkInterface
import com.jeong.android.coupang_eatsclone.src.main.bookmark.BookMarkRetrofitInterface
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.PatchBookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.PostBookMarkResponse
import java.lang.String.format

class StoreActivity: BaseActivity<ActivityStoreBinding>(ActivityStoreBinding::inflate),
            StoreInterface{

    private lateinit var storeReviewRecyclerViewAdapter: StoreReviewRecyclerViewAdapter
    private var reviewList = mutableListOf<Review>()
    private lateinit var storeOutRecyclerViewAdapter: StoreOutRecyclerViewAdapter
    private var revOutList = mutableListOf<MenuCategory>()
    private lateinit var storeCategoryRecyclerViewAdapter: StoreCategoryRecyclerViewAdapter
    private var categoryList = mutableListOf<MenuCategory>()
    private var bookmarkCheck : Int = 0
    private var storeId = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        storeId = intent.getIntExtra("storeId", -1)
        StoreService(this).tryGetStore(storeId)


        storeReviewRecyclerViewAdapter = StoreReviewRecyclerViewAdapter(reviewList)
        binding.revStoreReview.adapter = storeReviewRecyclerViewAdapter

        storeCategoryRecyclerViewAdapter = StoreCategoryRecyclerViewAdapter(categoryList)
        binding.revStoreCategory.adapter = storeCategoryRecyclerViewAdapter

        storeOutRecyclerViewAdapter = StoreOutRecyclerViewAdapter(revOutList)
        binding.revStoreMenuMain.adapter = storeOutRecyclerViewAdapter

        binding.icHeart.setOnClickListener {
            if (bookmarkCheck == 1) {
                binding.icHeart.setImageResource(R.drawable.ic_heart_white)
                StoreService(this).tryDeleteBookmark(storeId)
            } else {
                binding.icHeart.setImageResource(R.drawable.ic_heart_white_selected)
                StoreService(this).tryPostBookmark(storeId)
            }
        }



    }

    override fun onGetStoreSuccess(response: StoreResponse) {

        binding.tvStoreName.text = response.result.store_name
        if (response.result.is_cheetah_delivery == "N") {
            binding.bgCheetah.visibility = View.GONE
        } else {
            binding.bgCheetah.visibility = View.VISIBLE
        }
        Glide.with(binding.root)
            .load(response.result.store_main_image_url)
            .into(binding.ivDetailStore)
        binding.tvDeliveryTime.text = response.result.delivery_time
        binding.tvDeliveryCost.text = response.result.start_delivery_fee.toString()
        binding.tvMinDeliveryCost.text = response.result.minimum_price.toString()
        binding.tvReviewAvg.text = format("%.1f", response.result.average)
        binding.tvReviewCnt.text = "(${response.result.cnt})"

        if (response.result.isBookMark == 1) {
            binding.icHeart.setImageResource(R.drawable.ic_heart_white_selected)
            bookmarkCheck = 1
        } else {
            binding.icHeart.setImageResource(R.drawable.ic_heart_white)
            bookmarkCheck = 0
        }
        categoryList = response.result.menuCategoryList.toMutableList()

        storeReviewRecyclerViewAdapter.addData(response.result.review)
        storeCategoryRecyclerViewAdapter.addData(categoryList)
        storeOutRecyclerViewAdapter.addData(response.result.menuCategoryList, storeId)

    }

    override fun onGetStoreFailure(message: String) {
        showCustomToast("오류: $message")
    }

    override fun onPostBookMarkSuccess(response: PostBookMarkResponse) {
//        showCustomToast("즐겨찾기에 추가되었습니다")
        bookmarkCheck = 1
    }

    override fun onPostBookMarkFailure(message: String) {
        showCustomToast("오류: $message")
    }

    override fun onDeleteBookMarkSuccess(response: PatchBookMarkResponse) {
//        showCustomToast("즐겨찾기에서 삭제되었습니다")
        bookmarkCheck = 0
    }

    override fun onDeleteBookMarkFailure(message: String) {
        showCustomToast("오류: $message")
    }
}