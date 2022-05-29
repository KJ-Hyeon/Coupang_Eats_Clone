package com.jeong.android.coupang_eatsclone.src.main.bookmark

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeong.android.coupang_eatsclone.databinding.ItemBookmarkBinding
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.BookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.ResultBookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.ReviewSimple
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.StoreSimple
import java.lang.String.format
import kotlin.math.log

class BookMarkRecyclerViewAdapter() :
                    RecyclerView.Adapter<BookMarkRecyclerViewAdapter.BookMarkRecyclerViewHolder>() {

    private var storeData = mutableListOf<StoreSimple>()
    private var reviewData = mutableListOf<ReviewSimple>()

    private lateinit var binding: ItemBookmarkBinding
    // 클릭이벤트
    private var listener: OnItemClickListener? = null

    // 클릭이벤트
    interface OnItemClickListener {
        fun onItemClick(v: View, Pos: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarkRecyclerViewHolder {
        binding = ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookMarkRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookMarkRecyclerViewHolder, position: Int) {
        holder.bind(storeData[position], reviewData[position])
    }

    override fun getItemCount(): Int {
        return storeData.size
    }

    fun addData(storeItem : List<StoreSimple>, reviewItem : List<ReviewSimple>) {
        storeData.addAll(storeItem)
        reviewData.addAll(reviewItem)
        notifyDataSetChanged()
        Log.e(TAG, "addData: ${storeData.size}")
        Log.e(TAG, "addData: ${reviewData.size}")
    }

    // 클릭이벤트
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class BookMarkRecyclerViewHolder(private val binding: ItemBookmarkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(storeItem : StoreSimple, reviewItem : ReviewSimple ) {

            Glide.with(binding.root)
                .load(storeItem.store_main_image_url)
                .into(binding.ivRevBookmark)
            binding.tvRevBookmarkName.text = storeItem.store_name
            if (storeItem.is_cheetah_delivery == "N") {
                binding.ivRevBookmarkCheetah.visibility = View.INVISIBLE
            } else {
                binding.ivRevBookmarkCheetah.visibility = View.VISIBLE
            }
            if (storeItem.start_delivery_fee == 0) {
                binding.tvRevBookmarkDeliveryFee.text = "무료배달~"
            } else {
                binding.tvRevBookmarkDeliveryFee.text = "배달비 ${storeItem.start_delivery_fee}원"
            }
            // 시간이 빠짐
            binding.tvRevBookmarkReviewAvg.text = format("%.1f", reviewItem.review_avg)
            binding.tvRevBookmarkReviewCnt.text = "(${reviewItem.review_cnt})"


            // 클릭 이벤트
            binding.root.setOnClickListener {
                listener?.onItemClick(binding.root, adapterPosition)
            }
        }
    }
}