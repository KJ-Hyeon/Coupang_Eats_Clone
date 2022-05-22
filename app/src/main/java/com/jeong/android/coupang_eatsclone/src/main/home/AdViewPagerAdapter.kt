package com.jeong.android.coupang_eatsclone.src.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeong.android.coupang_eatsclone.databinding.ItemViewpagerAdBinding

class AdViewPagerAdapter(private val data: ArrayList<Int>) : RecyclerView.Adapter<AdViewPagerAdapter.AdViewPagerViewHolder>() {

    private lateinit var binding: ItemViewpagerAdBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewPagerViewHolder {
        binding = ItemViewpagerAdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdViewPagerViewHolder, position: Int) {
        // 일반 뷰페이저
//        holder.bind(data[position])
        // 무한 뷰페이저(position에 3을 나눈 나머지 값을 사용한다.)
        val page = data.size
        holder.bind(data[position%page])
    }

    override fun getItemCount(): Int {
        // 일반 뷰페이저
//        return data.size
        // 무한 뷰페이저(아이템의 갯수를 임의로 확 늘린다.)
        return Int.MAX_VALUE
    }

    inner class AdViewPagerViewHolder(private val binding: ItemViewpagerAdBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            binding.ivBanner.setImageResource(image)
        }
    }
}