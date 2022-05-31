package com.jeong.android.coupang_eatsclone.src.main.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeong.android.coupang_eatsclone.databinding.ItemOrderMenuInRadioBinding
import com.jeong.android.coupang_eatsclone.src.main.order.models.MenuOption

class OrderRecyclerViewAdapter(private val data: MutableList<MenuOption>) :
    RecyclerView.Adapter<OrderRecyclerViewAdapter.OrderRecyclerViewHolder>() {

    private var selectCheck: ArrayList<Int> = arrayListOf()
    private lateinit var binding: ItemOrderMenuInRadioBinding

    // 클릭이벤트
    private var listener: OnItemClickListener? = null

    // 클릭이벤트
    interface OnItemClickListener {
        fun onItemClick(v: View, Pos: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderRecyclerViewAdapter.OrderRecyclerViewHolder {
        binding = ItemOrderMenuInRadioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderRecyclerViewHolder(binding)
    }

        override fun onBindViewHolder(holder: OrderRecyclerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addData(item: List<MenuOption>) {
        data.addAll(item)
        for (i in data) {
            selectCheck.add(0)
        }
        notifyDataSetChanged()
    }


    // 클릭이벤트
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class OrderRecyclerViewHolder(private val binding: ItemOrderMenuInRadioBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuOption ) {
            binding.optionMenuName.text = item.option_name
            binding.optionMenuPrice.text = "(+${item.option_price}원)"
            // 클릭 이벤트
            binding.btnMenuRadio.apply {
                isChecked = selectCheck[adapterPosition] == 1
                setOnClickListener {
                    for (i in selectCheck.indices) {
                        if (i == adapterPosition) {
                            selectCheck[i] = 1
                        } else {
                            selectCheck[i] = 0
                        }
                    }
                    listener?.onItemClick(binding.root, item.menu_option_id)
                    notifyDataSetChanged()
                }
            }
        }
    }
}