package com.jeong.android.coupang_eatsclone.src.main.page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeong.android.coupang_eatsclone.databinding.ItemViewpagerAdBinding

class PageAdViewPagerAdapter(private val data: ArrayList<Int>) : RecyclerView.Adapter<PageAdViewPagerAdapter.PageAdViewPagerViewHolder>() {

    private lateinit var binding: ItemViewpagerAdBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageAdViewPagerViewHolder {
        binding = ItemViewpagerAdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PageAdViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PageAdViewPagerViewHolder, position: Int) {
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

    inner class PageAdViewPagerViewHolder(private val binding: ItemViewpagerAdBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            binding.ivBanner.setImageResource(image)
        }
    }
}