package com.jeong.android.coupang_eatsclone.src.main.home

import android.util.Log
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.src.main.cart.models.CartResponse
import com.jeong.android.coupang_eatsclone.src.main.home.models.HomeStore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val homeFragmentInterface: HomeFragmentInterface) {

    fun tryGetStore(){
        val HomeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        HomeRetrofitInterface.getStores().enqueue(object : Callback<HomeStore>{

            override fun onResponse(call: Call<HomeStore>, response: Response<HomeStore>) {
                if (response.isSuccessful) {
                    homeFragmentInterface.onGetStoreSuccess(response.body() as HomeStore)
                } else {
                    Log.e("TAG", "onResponse:${response.message()}", )
                }
            }
            override fun onFailure(call: Call<HomeStore>, t: Throwable) {
                homeFragmentInterface.onGetStoreFailure(t.message ?: "통신오류")
            }
        })
    }

    fun tryGetCart() {
        val HomeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        HomeRetrofitInterface.getCart().enqueue(object : Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                if (response.isSuccessful) {
                    homeFragmentInterface.onGetCartSuccess(response.body() as CartResponse)
                } else {
                    Log.e("TAG", "onResponse:${response.message()}", )
                }
            }

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                homeFragmentInterface.onGetCartFailure(t.message ?: "통신오류")
            }
        })
    }

}