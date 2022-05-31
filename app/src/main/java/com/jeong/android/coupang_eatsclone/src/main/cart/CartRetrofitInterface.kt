package com.jeong.android.coupang_eatsclone.src.main.cart

import com.jeong.android.coupang_eatsclone.src.main.cart.models.CartResponse
import com.jeong.android.coupang_eatsclone.src.main.cart.models.PatchCartRequest
import com.jeong.android.coupang_eatsclone.src.main.cart.models.PatchCartResponse
import com.jeong.android.coupang_eatsclone.src.main.home.models.HomeStore
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query

interface CartRetrofitInterface {

    @GET("/orders/carts")
    fun getCart() : Call<CartResponse>

    @PATCH("/orders/carts")
    fun patchCart(
        @Body params: PatchCartRequest,
        @Query ("storeIdx") storeIdx : Int,
        @Query("cartIdx") cartIdx : Int
    ) : Call<PatchCartResponse>
}
