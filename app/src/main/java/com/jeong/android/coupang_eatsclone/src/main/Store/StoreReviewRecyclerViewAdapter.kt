package com.jeong.android.coupang_eatsclone.src.main.Store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeong.android.coupang_eatsclone.databinding.ItemStoreReviewBinding
import com.jeong.android.coupang_eatsclone.databinding.ItemViewpagerAdBinding
import com.jeong.android.coupang_eatsclone.src.main.Store.models.Review
import com.jeong.android.coupang_eatsclone.src.main.home.models.Result

class StoreReviewRecyclerViewAdapter(private val data: MutableList<Review>) : RecyclerView.Adapter<StoreReviewRecyclerViewAdapter.StoreReviewRecyclerViewHolder>() {

    private lateinit var binding: ItemStoreReviewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreReviewRecyclerViewHolder {
        binding = ItemStoreReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreReviewRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreReviewRecyclerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addData(item: List<Review>) {
        data.addAll(item)
        notifyDataSetChanged()
    }

    inner class StoreReviewRecyclerViewHolder(private val binding: ItemStoreReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Review) {
            Glide.with(binding.root)
                .load(item.review_image_url)
                .into(binding.ivStoreReview)
            binding.tvStoreContent.text = item.review_content
            binding.reviewRatingBar.rating = item.review_star.toFloat()
        }
    }
}