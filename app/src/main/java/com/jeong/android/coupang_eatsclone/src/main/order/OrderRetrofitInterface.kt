package com.jeong.android.coupang_eatsclone.src.main.order

import com.jeong.android.coupang_eatsclone.src.main.order.models.OrderResponse
import com.jeong.android.coupang_eatsclone.src.main.order.models.PostAddCartRequest
import com.jeong.android.coupang_eatsclone.src.main.order.models.PostAddCartResponse
import retrofit2.Call
import retrofit2.http.*

interface OrderRetrofitInterface {

    @GET("/store/{storeIdx}/{menuIdx}")
    fun getMenu(
        @Path("storeIdx") storeIdx: Int,
        @Path("menuIdx") menuIdx: Int,
    ): Call<OrderResponse>

    @POST("/order/cart")
    fun postAddcart(
        @Body params: PostAddCartRequest,
        @Query("storeIdx") storeIdx: Int,
        @Query("menuIdx") menuIdx: Int
    ): Call<PostAddCartResponse>
}
