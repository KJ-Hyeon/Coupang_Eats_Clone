package com.jeong.android.coupang_eatsclone.src.main.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeong.android.coupang_eatsclone.databinding.ItemHomeStoreBinding
import com.jeong.android.coupang_eatsclone.src.main.home.models.HomeStore
import com.jeong.android.coupang_eatsclone.src.main.home.models.Result

class HomeRecyclerViewAdapter(private val data: List<Result>) :
    RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeRecyclerViewHolder>() {

    private lateinit var binding: ItemHomeStoreBinding

    // 클릭이벤트
    private var listener: OnItemClickListener? = null

    // 클릭이벤트
    interface OnItemClickListener {
        fun onItemClick(v: View, data: String, Pos: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        binding = ItemHomeStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }


    // 클릭이벤트
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class HomeRecyclerViewHolder(private val binding: ItemHomeStoreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result) {
            Log.e("TAG", "bind:${item.store_main_image_url} ")
            Glide.with(binding.root)
                .load(item.store_main_image_url)
                .into(binding.homeRevMainImage)
            binding.homeRevStoreName.text = item.store_name
            binding.homeRevReview.text = item.rcnt.toString()
            binding.homeRevRate.text = item.ravg.toString()
            binding.homeRevTime.text = item.delivery_time
            if (item.is_cheetah_delivery == "N") {
                binding.homeRevCheetah.visibility = View.GONE
            } else {
                binding.homeRevCheetah.visibility = View.VISIBLE
            }
            if (item.take_out == "N") {
                binding.homeRevCheetah.visibility = View.GONE
            } else {
                binding.homeRevCheetah.visibility = View.VISIBLE
            }

            // 클릭 이벤트
            binding.root.setOnClickListener {
//                listener?.onItemClick(binding.root, str, adapterPosition)
            }
        }
    }
}