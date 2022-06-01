package com.jeong.android.coupang_eatsclone.src.main.cart

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.ApplicationClass.Companion.sSharedPreferences
import com.jeong.android.coupang_eatsclone.config.BaseFragment
import com.jeong.android.coupang_eatsclone.databinding.FragmentCartDeliveryBinding
import com.jeong.android.coupang_eatsclone.src.main.cart.models.*

class CartDeliveryFrgment :BaseFragment<FragmentCartDeliveryBinding>(FragmentCartDeliveryBinding::bind, R.layout.fragment_cart_delivery),
            CartInterface{

    private lateinit var cartRecyclerViewAdapter: CartRecyclerViewAdapter
    private var cartMenuList = mutableListOf<CartMenu>()
    private var storeId = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CartService(this).tryGetCart()

        val mainAddress = sSharedPreferences.getString("mainAddress",null)
        val roadAddress = sSharedPreferences.getString("RoadAddress",null)
        binding?.tvCartMainAddress?.text = mainAddress
        if (roadAddress.isNullOrEmpty()) {
            binding?.tvCartRoadAddress?.text = "화성시 병점3로 132-6"
        } else {
            binding?.tvCartRoadAddress?.text = roadAddress
        }

        cartRecyclerViewAdapter = CartRecyclerViewAdapter(cartMenuList,requireContext())
        binding?.revCartMenu?.adapter = cartRecyclerViewAdapter

        cartRecyclerViewAdapter.setOnItemClickListener(object : CartRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: CartMenu, amount: Int) {
                revClick(data, amount)
            }
            override fun onDeletClick(data: CartMenu) {
                val mDialog = LayoutInflater.from(context).inflate(R.layout.dialog_delete,null)
                val builder = AlertDialog.Builder(context)
                    .setView(mDialog)

                val customDeleteDialog = builder.show()
                val deleteBtn = customDeleteDialog.findViewById<TextView>(R.id.btn_delete)
                val cancleBtn = customDeleteDialog.findViewById<TextView>(R.id.btn_cancle)

                cancleBtn.setOnClickListener {
                    customDeleteDialog.dismiss()
                }
                deleteBtn.setOnClickListener {
                    revDeleteClick(data)
                    customDeleteDialog.dismiss()
                }
            }
        })
    }

    fun revClick(data: CartMenu, amount: Int) {
        val patchCartRequest = PatchCartRequest(amount)
        CartService(this).tryPatchCart(patchCartRequest, storeId, data.cart_id )
    }
    fun revDeleteClick(data: CartMenu) {
        val deleteCartRequest = DeleteCartRequest(data.cart_id)
        CartService(this).trydeleteCart(deleteCartRequest)
    }

    override fun onGetCartSuccess(response: CartResponse) {
        binding?.tvCartStoreName?.text = response.result.store_name
        if (response.result.is_cheetah_delivery == "N") {
            binding?.cartCheetah?.visibility = View.INVISIBLE
        } else {
            binding?.cartCheetah?.visibility = View.VISIBLE
        }
//        binding?.btnOrder?.text = response.result.
        cartRecyclerViewAdapter.clearData()
        cartRecyclerViewAdapter.addData(response.result.cartMenu)
        storeId = response.result.store_id
    }

    override fun onGetCartNull() {
        cartRecyclerViewAdapter.clearData()
        val intent = Intent(requireContext(), CartEmptyActivity::class.java)
        requireContext().startActivity(intent)
    }

    override fun onGetCartFailure(message: String) {
        showCustomToast("$message")
    }

    override fun onPatchCartSuccess(response: PatchCartResponse) {
        cartRecyclerViewAdapter.clearData()
        CartService(this).tryGetCart()
    }

    override fun onPatchCartFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onDeleteCartSuccess(response: DeleteCartResponse) {
        CartService(this).tryGetCart()
    }

    override fun onDeleteCartFailure(message: String) {
        TODO("Not yet implemented")
    }
}