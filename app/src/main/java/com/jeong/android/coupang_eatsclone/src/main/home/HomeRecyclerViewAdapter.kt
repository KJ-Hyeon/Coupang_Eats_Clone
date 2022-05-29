package com.jeong.android.coupang_eatsclone.src.main.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeong.android.coupang_eatsclone.databinding.ItemHomeStoreBinding
import com.jeong.android.coupang_eatsclone.src.main.adress.models.ResultAddressList
import com.jeong.android.coupang_eatsclone.src.main.home.models.HomeStore
import com.jeong.android.coupang_eatsclone.src.main.home.models.Result

class HomeRecyclerViewAdapter(private val data: MutableList<Result>) :
    RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeRecyclerViewHolder>() {

    private lateinit var binding: ItemHomeStoreBinding

    // 클릭이벤트
    private var listener: OnItemClickListener? = null

    // 클릭이벤트
    interface OnItemClickListener {
        fun onItemClick(v: View, Pos: Int)
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

    fun addData(item: List<Result>) {
        data.addAll(item)
        notifyDataSetChanged()
    }


    // 클릭이벤트
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class HomeRecyclerViewHolder(private val binding: ItemHomeStoreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result) {
//            Log.e("TAG", "bind:${item.store_main_image_url} ")
            Glide.with(binding.root)
                .load(item.store_main_image_url)
                .into(binding.homeRevMainImage)
            binding.homeRevStoreName.text = item.store_name
            binding.homeRevReview.text = "(${item.rcnt})"
            binding.homeRevRate.text = String.format("%.1f", item.ravg)
            binding.homeRevTime.text = item.delivery_time
            if (item.start_delivery_fee == 0) {
                binding.homeRevDelivertCost.text = "무료배달~"
            } else {
                binding.homeRevDelivertCost.text = "배달비${item.start_delivery_fee}원"
            }
            if (item.is_cheetah_delivery == "N") {
                binding.homeRevCheetah.visibility = View.GONE
                Log.e("TAG", "bind:isCheetahN", )
            } else {
                binding.homeRevCheetah.visibility = View.VISIBLE
                Log.e("TAG", "bind:isCheetahY", )
            }
            if (item.take_out == "N") {
                binding.homeRevDelivery.visibility = View.GONE
            } else {
                binding.homeRevDelivery.visibility = View.VISIBLE
            }

            // 클릭 이벤트
            binding.root.setOnClickListener {
                listener?.onItemClick(binding.root, adapterPosition)
            }
        }
    }
}