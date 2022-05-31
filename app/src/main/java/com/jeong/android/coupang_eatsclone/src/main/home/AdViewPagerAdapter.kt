package com.jeong.android.coupang_eatsclone.src.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeong.android.coupang_eatsclone.databinding.ItemViewpagerAdBinding
import com.jeong.android.coupang_eatsclone.src.main.page.models.Ad

class AdViewPagerAdapter(private val data: MutableList<Int>) : RecyclerView.Adapter<AdViewPagerAdapter.AdViewPagerViewHolder>() {

    private lateinit var binding: ItemViewpagerAdBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewPagerViewHolder {
        binding = ItemViewpagerAdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdViewPagerViewHolder, position: Int) {
        val page = data.size
        holder.bind(data[position%page])
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    inner class AdViewPagerViewHolder(private val binding: ItemViewpagerAdBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Int) {
            binding.ivBanner.setImageResource(item)
        }
    }
}