package com.jeong.android.coupang_eatsclone.src.main.order

import android.content.ContentValues.TAG
import android.util.Log
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.src.main.order.models.OrderResponse
import com.jeong.android.coupang_eatsclone.src.main.order.models.PostAddCartRequest
import com.jeong.android.coupang_eatsclone.src.main.order.models.PostAddCartResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderService(val orderInterface: OrderInterface) {

    fun tryGetMenu(storeIndex: Int, menuIndex: Int){
        val OrderRetrofitInterface = ApplicationClass.sRetrofit.create(OrderRetrofitInterface::class.java)
        OrderRetrofitInterface.getMenu(storeIndex, menuIndex).enqueue(object : Callback<OrderResponse>{
            override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                if (response.isSuccessful) {
                    orderInterface.onGetDetailMenuSuccess(response.body() as OrderResponse)
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                orderInterface.onGetDetailMenuFailure(t.message ?: "통신오류")
            }
        })
    }

    fun tryPostAddCart(postAddCartRequest: PostAddCartRequest, storeIndex: Int, menuIndex: Int) {
        val OrderRetrofitInterface = ApplicationClass.sRetrofit.create(OrderRetrofitInterface::class.java)
        OrderRetrofitInterface.postAddcart(postAddCartRequest,storeIndex,menuIndex).enqueue(object : Callback<PostAddCartResponse>{
            override fun onResponse(
                call: Call<PostAddCartResponse>,
                response: Response<PostAddCartResponse>
            ) {
                if (response.isSuccessful) {
                    orderInterface.onPostAddCartSuccess(response.body() as PostAddCartResponse)
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PostAddCartResponse>, t: Throwable) {
                orderInterface.onPostAddCartFailure(t.message ?: "통신오류")
            }
        })
    }

}