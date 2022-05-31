package com.jeong.android.coupang_eatsclone.src.main.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeong.android.coupang_eatsclone.databinding.ItemCartMenuBinding
import com.jeong.android.coupang_eatsclone.src.main.cart.models.*

class CartRecyclerViewAdapter(private val data: MutableList<CartMenu>, val context: Context) :
    RecyclerView.Adapter<CartRecyclerViewAdapter.CartRecyclerViewHolder>(){

    private lateinit var binding: ItemCartMenuBinding
    private var storeId: Int = -1

    // 클릭이벤트
    private var listener: OnItemClickListener? = null

    // 클릭이벤트
    interface OnItemClickListener {
        fun onItemClick(v: View,data: CartMenu, amount:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartRecyclerViewHolder {
        binding = ItemCartMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartRecyclerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addData(item: List<CartMenu>) {
        data.addAll(item)
        this.storeId = storeId
        notifyDataSetChanged()
    }


    // 클릭이벤트
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class CartRecyclerViewHolder(private val binding: ItemCartMenuBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: CartMenu) {
            binding.menuName.text = item.menu_name
            binding.menuOption.text = item.option_name
            binding.menuPrice.text = "${item.price}원"
            binding.tvAmount.setOnClickListener {
                val amountDialog = CustomAmountDialog(context)
                amountDialog.showDialog()
                amountDialog.setOnClickListener(object : CustomAmountDialog.TextClickListener{
                    override fun onClick(text: String) {
                        binding.tvAmount.text = text
                        listener?.onItemClick(binding.root,item,binding.tvAmount.text.toString().toInt())
                    }
                })
            }
        }
    }
}