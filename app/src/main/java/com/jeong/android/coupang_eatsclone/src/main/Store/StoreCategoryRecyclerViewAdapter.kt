package com.jeong.android.coupang_eatsclone.src.main.Store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeong.android.coupang_eatsclone.databinding.ItemStoreCategoryBinding
import com.jeong.android.coupang_eatsclone.databinding.ItemViewpagerAdBinding
import com.jeong.android.coupang_eatsclone.src.main.Store.models.Review

class StoreCategoryRecyclerViewAdapter(private val data: ArrayList<Int>) : RecyclerView.Adapter<StoreCategoryRecyclerViewAdapter.StoreCategoryRecyclerViewHolder>() {

    private lateinit var binding: ItemStoreCategoryBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreCategoryRecyclerViewHolder {
        binding = ItemStoreCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreCategoryRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreCategoryRecyclerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

//    fun addData(item: List<Review>) {
//        data.addAll(item)
//        notifyDataSetChanged()
//    }

    inner class StoreCategoryRecyclerViewHolder(private val binding: ItemStoreCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {

        }
    }
}