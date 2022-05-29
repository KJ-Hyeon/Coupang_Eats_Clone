package com.jeong.android.coupang_eatsclone.src.main.Store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeong.android.coupang_eatsclone.databinding.ItemStoreMenuOutBinding
import com.jeong.android.coupang_eatsclone.src.main.Store.models.MenuCategory

class StoreOutRecyclerViewAdapter(private val data: MutableList<MenuCategory>) : RecyclerView.Adapter<StoreOutRecyclerViewAdapter.StoreOutRecyclerViewHolder>() {

    private lateinit var binding: ItemStoreMenuOutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreOutRecyclerViewHolder {
        binding = ItemStoreMenuOutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreOutRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreOutRecyclerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun addData(item: List<MenuCategory>) {
        data.addAll(item)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class StoreOutRecyclerViewHolder(private val binding: ItemStoreMenuOutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuCategory) {
            binding.storeCategory.text = item.keywordName
            binding.revStoreMenuContent.adapter = StoreInRecyclerViewAdapter(item.menuDetailList)
        }
    }
}