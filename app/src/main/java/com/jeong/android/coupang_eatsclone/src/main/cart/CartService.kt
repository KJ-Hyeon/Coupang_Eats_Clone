package com.jeong.android.coupang_eatsclone.src.main.cart

import android.util.Log
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.src.main.cart.models.CartResponse
import com.jeong.android.coupang_eatsclone.src.main.cart.models.PatchCartRequest
import com.jeong.android.coupang_eatsclone.src.main.cart.models.PatchCartResponse
import com.jeong.android.coupang_eatsclone.src.main.home.models.HomeStore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartService(val cartInterface: CartInterface) {

    fun tryGetCart() {
        val CartRetrofitInterface = ApplicationClass.sRetrofit.create(CartRetrofitInterface::class.java)
        CartRetrofitInterface.getCart().enqueue(object : Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                if (response.isSuccessful) {
                    cartInterface.onGetCartSuccess(response.body() as CartResponse)
                } else {
                    Log.e("TAG", "onResponse:${response.body()}", )
                }
            }

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                cartInterface.onGetCartFailure(t.message ?: "통신오류")
            }
        })
    }

    fun tryPatchCart(patchCartRequest: PatchCartRequest, storeId: Int, cartId: Int) {
        val CartRetrofitInterface = ApplicationClass.sRetrofit.create(CartRetrofitInterface::class.java)
        CartRetrofitInterface.patchCart(patchCartRequest, storeId, cartId).enqueue(object : Callback<PatchCartResponse>{
            override fun onResponse(
                call: Call<PatchCartResponse>,
                response: Response<PatchCartResponse>
            ) {
                if (response.isSuccessful) {
                    cartInterface.onPatchCartSuccess(response.body() as PatchCartResponse)
                } else {
                    Log.e("TAG", "onResponse:${response.message()}", )
                }
            }

            override fun onFailure(call: Call<PatchCartResponse>, t: Throwable) {
                cartInterface.onGetCartFailure(t.message ?: "통신오류")
            }
        })
    }

}