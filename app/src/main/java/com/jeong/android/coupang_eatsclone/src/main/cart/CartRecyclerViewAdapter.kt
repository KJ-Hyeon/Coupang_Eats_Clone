package com.jeong.android.coupang_eatsclone.src.main.cart

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.databinding.ItemCartMenuBinding
import com.jeong.android.coupang_eatsclone.src.main.cart.models.*

class CartRecyclerViewAdapter(private val data: MutableList<CartMenu>, val context: Context) :
    RecyclerView.Adapter<CartRecyclerViewAdapter.CartRecyclerViewHolder>(){

    private lateinit var binding: ItemCartMenuBinding
//    private var storeId: Int = -1

    // 클릭이벤트
    private var listener: OnItemClickListener? = null

    // 클릭이벤트
    interface OnItemClickListener {
        fun onItemClick(v: View,data: CartMenu, amount:Int)
        fun onDeletClick(data: CartMenu)
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
//        this.storeId = storeId
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

    inner class CartRecyclerViewHolder(private val binding: ItemCartMenuBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: CartMenu) {
            binding.menuName.text = item.menu_name
            binding.menuOption.text = item.option_name
            binding.menuPrice.text = "${item.price}원"
            binding.tvAmount.text = item.menu_count.toString()
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
            binding.icDelete.setOnClickListener {
                listener?.onDeletClick(item)
//                val builder = AlertDialog.Builder(context)
//                    .setMessage("선택하신 메뉴를 삭제하시겠습니까?")
//                    .setPositiveButton("삭제",
//                    DialogInterface.OnClickListener { dialogInterface, i ->
//                        // 삭제 버튼 눌렀을경우
//                    })
//                    .setNegativeButton("취소",
//                    DialogInterface.OnClickListener { dialogInterface, i ->
//
//                    })
//                builder.show()


//                val mDialog = LayoutInflater.from(context).inflate(R.layout.dialog_delete,null)
//                val builder = AlertDialog.Builder(context)
//                    .setView(mDialog)
//
//                val customDeleteDialog = builder.show()
//                val deleteBtn = customDeleteDialog.findViewById<TextView>(R.id.btn_delete)
//                val cancleBtn = customDeleteDialog.findViewById<TextView>(R.id.btn_cancle)
//
//                cancleBtn.setOnClickListener {
//                    customDeleteDialog.dismiss()
//                }
//                deleteBtn.setOnClickListener {
//                    val deleteCartRequest = DeleteCartRequest(item.cart_id)
//                    CartService(this).trydeleteCart(deleteCartRequest)
//                    customDeleteDialog.dismiss()
                }
            }
        }

//        override fun onGetCartSuccess(response: CartResponse) {
//            clearData()
//            addData(response.result.cartMenu)
//        }
//
//        override fun onGetCartNull() {
//            clearData()
//            // 카트가 비었다고 출력 (빈 카트 프래그먼트)
//        }
//
//        override fun onGetCartFailure(message: String) {
//            TODO("Not yet implemented")
//        }
//
//        override fun onPatchCartSuccess(response: PatchCartResponse) {
//            TODO("Not yet implemented")
//        }
//
//        override fun onPatchCartFailure(message: String) {
//            TODO("Not yet implemented")
//        }
//
//        override fun onDeleteCartSuccess(response: DeleteCartResponse) {
//            CartService(this).tryGetCart()
//        }
//
//        override fun onDeleteCartFailure(message: String) {
//            TODO("Not yet implemented")
//        }
    }