package com.jeong.android.coupang_eatsclone.src.main.cart

import com.jeong.android.coupang_eatsclone.src.main.cart.models.*
import com.jeong.android.coupang_eatsclone.src.main.home.models.HomeStore
import retrofit2.Call
import retrofit2.http.*

interface CartRetrofitInterface {

    @GET("/orders/carts")
    fun getCart() : Call<CartResponse>

    @PATCH("/orders/carts")
    fun patchCart(
        @Body params: PatchCartRequest,
        @Query ("storeIdx") storeIdx : Int,
        @Query("cartIdx") cartIdx : Int
    ) : Call<PatchCartResponse>

    @PATCH("/orders/carts/status")
    fun deleteCart(
        @Body params: DeleteCartRequest
    ) : Call<DeleteCartResponse>

    @POST("/orders")
    fun postCart(
        @Body params : PostCartRequest,
        @Query("cartList") cartList : List<Int>
    ) : Call<PostCartResponse>
}
