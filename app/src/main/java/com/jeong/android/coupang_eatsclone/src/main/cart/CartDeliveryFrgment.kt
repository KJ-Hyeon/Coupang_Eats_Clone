package com.jeong.android.coupang_eatsclone.src.main.cart

import android.os.Bundle
import android.view.View
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.ApplicationClass.Companion.sSharedPreferences
import com.jeong.android.coupang_eatsclone.config.BaseFragment
import com.jeong.android.coupang_eatsclone.databinding.FrgmentCartDeliveryBinding
import com.jeong.android.coupang_eatsclone.src.main.cart.models.*

class CartDeliveryFrgment :BaseFragment<FrgmentCartDeliveryBinding>(FrgmentCartDeliveryBinding::bind, R.layout.frgment_cart_delivery),
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
        })
    }

    fun revClick(data: CartMenu, amount: Int) {
        val patchCartRequest = PatchCartRequest(amount)
        CartService(this).tryPatchCart(patchCartRequest, storeId, data.cart_id )

    }

    override fun onGetCartSuccess(response: CartResponse) {
        binding?.tvCartStoreName?.text = response.result.store_name
        if (response.result.is_cheetah_delivery == "N") {
            binding?.cartCheetah?.visibility = View.INVISIBLE
        } else {
            binding?.cartCheetah?.visibility = View.VISIBLE
        }
//        binding?.btnOrder?.text = response.result.
        cartRecyclerViewAdapter.addData(response.result.cartMenu)
        storeId = response.result.store_id
    }

    override fun onGetCartFailure(message: String) {
        showCustomToast("$message")
    }

    override fun onPatchCartSuccess(response: PatchCartResponse) {
        // 수정 성공시 텍스트 숫자 바꿈
    }

    override fun onPatchCartFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onDeleteCartSuccess(response: DeleteCartResponse) {
        TODO("Not yet implemented")
    }

    override fun onDeleteCartFailure(message: String) {
        TODO("Not yet implemented")
    }
}