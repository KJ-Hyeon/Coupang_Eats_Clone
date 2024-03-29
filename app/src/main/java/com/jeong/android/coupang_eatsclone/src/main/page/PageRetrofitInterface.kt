package com.jeong.android.coupang_eatsclone.src.main.page

import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressListResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.*
import retrofit2.Call
import retrofit2.http.*

interface PageRetrofitInterface {

    @GET("/users/my-eats")
    fun getUser(): Call<UserResponse>

//    @GET("/users/address")
//    fun getAddressList(): Call<AddressListResponse>

    // 상세조회
    @GET("/users/addresses/{addressIdx}")
    fun getDetailAddress(@Path("addressIdx") addressIdx: Int): Call<DetailAddressResponse>

    @POST("/users/logout")
    fun postLogout() : Call<PostLogoutResponse>
}
