package com.jeong.android.coupang_eatsclone.src.main.Store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeong.android.coupang_eatsclone.databinding.ItemStoreMenuInBinding
import com.jeong.android.coupang_eatsclone.databinding.ItemViewpagerAdBinding
import com.jeong.android.coupang_eatsclone.src.main.Store.models.Review

class StoreInRecyclerViewAdapter(private val data: ArrayList<Int>) : RecyclerView.Adapter<StoreInRecyclerViewAdapter.StoreInRecyclerViewHolder>() {

    private lateinit var binding: ItemStoreMenuInBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreInRecyclerViewHolder {
        binding = ItemStoreMenuInBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreInRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreInRecyclerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

//    fun addData(item: List<Review>) {
//        data.addAll(item)
//        notifyDataSetChanged()
//    }

    inner class StoreInRecyclerViewHolder(private val binding: ItemStoreMenuInBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
//            binding
        }
    }
}