package com.jeong.android.coupang_eatsclone.src.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeong.android.coupang_eatsclone.databinding.ItemViewpagerAdBinding
import com.jeong.android.coupang_eatsclone.databinding.ItemViewpagerIntroBinding

class IntroViewpagerAdapter(private val data: ArrayList<Int>) : RecyclerView.Adapter<IntroViewpagerAdapter.IntroViewpagerViewHolder>() {

    private lateinit var binding: ItemViewpagerIntroBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewpagerViewHolder {
        binding = ItemViewpagerIntroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IntroViewpagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IntroViewpagerViewHolder, position: Int) {
        // 일반 뷰페이저
        holder.bind(data[position])
        // 무한 뷰페이저(position에 3을 나눈 나머지 값을 사용한다.)
//        val page = data.size
//        holder.bind(data[position%page])
    }

    override fun getItemCount(): Int {
        // 일반 뷰페이저
        return data.size
        // 무한 뷰페이저(아이템의 갯수를 임의로 확 늘린다.)
//        return Int.MAX_VALUE
    }

    inner class IntroViewpagerViewHolder(private val binding: ItemViewpagerIntroBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            Glide.with(binding.root)
                .load(image)
                .into(binding.bgIntro)
        }
    }
}