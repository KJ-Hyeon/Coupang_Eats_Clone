package com.jeong.android.coupang_eatsclone.src.main.Store

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityStoreBinding
import com.jeong.android.coupang_eatsclone.src.main.Store.models.Review
import com.jeong.android.coupang_eatsclone.src.main.Store.models.StoreResponse
import com.jeong.android.coupang_eatsclone.src.main.home.HomeRecyclerViewAdapter
import com.jeong.android.coupang_eatsclone.src.main.home.models.Result

class StoreActivity: BaseActivity<ActivityStoreBinding>(ActivityStoreBinding::inflate),
            StoreInterface{

    private lateinit var storeReviewRecyclerViewAdapter: StoreReviewRecyclerViewAdapter
    private var reviewList = mutableListOf<Review>()
    private lateinit var storeOutRecyclerViewAdapter: StoreOutRecyclerViewAdapter
//    private var revOutList = mutableListOf<Review>()
    private lateinit var storeCategoryRecyclerViewAdapter: StoreCategoryRecyclerViewAdapter
//    private var categoryList = mutableListOf<>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val pos = intent.getIntExtra("Pos", -1)
        StoreService(this).tryGetStore(pos)

        storeReviewRecyclerViewAdapter = StoreReviewRecyclerViewAdapter(reviewList)
        binding.revStoreReview.adapter = storeReviewRecyclerViewAdapter

//        binding.icHeart.setOnClickListener {
//            binding.icHeart.setImageResource(R.drawable.ic_heart_white_selected)
//        }



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
        binding.tvReviewAvg.text = response.result.average.toString()
        binding.tvReviewCnt.text = response.result.cnt.toString()

        storeReviewRecyclerViewAdapter.addData(response.result.review)



    }

    override fun onGetStoreFailure(message: String) {
        TODO("Not yet implemented")
    }
}