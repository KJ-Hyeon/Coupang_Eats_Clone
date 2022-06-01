package com.jeong.android.coupang_eatsclone.src.main.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeong.android.coupang_eatsclone.databinding.ItemOrderMenuInRadioBinding
import com.jeong.android.coupang_eatsclone.databinding.ItemRecentSearchBinding
import com.jeong.android.coupang_eatsclone.src.main.order.models.MenuOption
import com.jeong.android.coupang_eatsclone.src.main.search.models.Search

class SearchRecyclerViewAdapter(private val data: MutableList<Search>) :
    RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchRecyclerViewHolder>() {

    private lateinit var binding: ItemRecentSearchBinding

    // 클릭이벤트
    private var listener: OnItemClickListener? = null

    // 클릭이벤트
    interface OnItemClickListener {
        fun onItemClick(v: View, data: Search)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecyclerViewAdapter.SearchRecyclerViewHolder {
        binding = ItemRecentSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchRecyclerViewHolder(binding)
    }

        override fun onBindViewHolder(holder: SearchRecyclerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addData(item: List<Search>) {
        data.addAll(item)
        notifyDataSetChanged()
    }

    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }


    // 클릭이벤트
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class SearchRecyclerViewHolder(private val binding: ItemRecentSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Search ) {
            binding.tvSearchKeyword.text = item.category_name
            binding.tvRecentDate.text = item.created_at
            binding.icFinish.setOnClickListener {
                listener?.onItemClick(binding.icFinish, item)
            }
        }
    }
}