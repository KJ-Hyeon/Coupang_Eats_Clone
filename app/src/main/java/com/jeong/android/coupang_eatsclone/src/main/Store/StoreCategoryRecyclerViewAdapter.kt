package com.jeong.android.coupang_eatsclone.src.main.Store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeong.android.coupang_eatsclone.databinding.ItemStoreCategoryBinding
import com.jeong.android.coupang_eatsclone.src.main.Store.models.MenuCategory

class StoreCategoryRecyclerViewAdapter(private val data: MutableList<MenuCategory>) : RecyclerView.Adapter<StoreCategoryRecyclerViewAdapter.StoreCategoryRecyclerViewHolder>() {

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


    fun addData(item: List<MenuCategory>) {
        data.addAll(item)
        notifyDataSetChanged()
    }

    inner class StoreCategoryRecyclerViewHolder(private val binding: ItemStoreCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuCategory) {
            binding.tvStoreCategory.text = item.keywordName
        }
    }
}