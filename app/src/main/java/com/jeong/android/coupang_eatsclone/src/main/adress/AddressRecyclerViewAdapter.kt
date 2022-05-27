package com.jeong.android.coupang_eatsclone.src.main.adress

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeong.android.coupang_eatsclone.databinding.ItemAddressBinding
import com.jeong.android.coupang_eatsclone.databinding.ItemHomeStoreBinding
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.ResultAddressList
import com.jeong.android.coupang_eatsclone.src.main.home.models.HomeStore
import com.jeong.android.coupang_eatsclone.src.main.home.models.Result
import com.jeong.android.coupang_eatsclone.src.main.page.models.AddressPatchResponse

class AddressRecyclerViewAdapter(private val data: MutableList<ResultAddressList>) :
    RecyclerView.Adapter<AddressRecyclerViewAdapter.AddressRecyclerViewHolder>(){

    private lateinit var binding: ItemAddressBinding

    // 클릭이벤트
    private var listener: OnItemClickListener? = null

    // 클릭이벤트
    interface OnItemClickListener {
        fun onItemClick(v: View, data: ResultAddressList, Pos: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressRecyclerViewHolder {
        binding = ItemAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddressRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressRecyclerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addData(item: List<ResultAddressList>) {
        data.addAll(item)
        notifyDataSetChanged()
    }

    // 클릭이벤트
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class AddressRecyclerViewHolder(private val binding: ItemAddressBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultAddressList) {
            // 별칭의 유뮤에 따른  binding.tvAddressName.text setting
            binding.tvAddressName.text = item.main_address
            binding.tvAddressDetailName.text = item.main_address + item.detail_address
            // 클릭 이벤트
            binding.root.setOnClickListener {
                listener?.onItemClick(binding.root, item, adapterPosition)
                Log.e(TAG, "bind: RevClick", )

            }
        }
    }

}