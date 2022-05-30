package com.jeong.android.coupang_eatsclone.src.main.Store

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeong.android.coupang_eatsclone.databinding.ItemStoreMenuInBinding
import com.jeong.android.coupang_eatsclone.src.main.Store.models.MenuDetail
import com.jeong.android.coupang_eatsclone.src.main.order.OrderActivity

class StoreInRecyclerViewAdapter(private val data: List<MenuDetail>, val storeIndex: Int) :
    RecyclerView.Adapter<StoreInRecyclerViewAdapter.StoreInRecyclerViewHolder>() {

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

    inner class StoreInRecyclerViewHolder(private val binding: ItemStoreMenuInBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuDetail) {
            binding.tvStoreMenuName.text = item.menu_name
            Glide.with(binding.root)
                .load(item.menu_img_url)
                .into(binding.ivStoreMenu)
            binding.tvStoreMenuDetail.text = item.menu_description
            binding.tvStoreMenuPrice.text = "${item.menu_price}원"
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, OrderActivity::class.java)
                intent.putExtra("menuIndex",item.type)
                // 내일 이걸로 수정
                Log.e(TAG, "bind: ${item.menu_id}")
                intent.putExtra("storeIndex",storeIndex)
                binding.root.context.startActivity(intent)
            }
        }
    }
}