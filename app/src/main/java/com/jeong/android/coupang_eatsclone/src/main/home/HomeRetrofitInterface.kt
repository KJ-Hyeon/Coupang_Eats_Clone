package com.jeong.android.coupang_eatsclone.src.main.home

import com.jeong.android.coupang_eatsclone.src.main.cart.models.CartResponse
import com.jeong.android.coupang_eatsclone.src.main.home.models.HomeStore
import retrofit2.Call
import retrofit2.http.GET

interface HomeRetrofitInterface {

    @GET("/stores/home")
    fun getStores(): Call<HomeStore>

    @GET("/orders/carts")
    fun getCart() : Call<CartResponse>
}
