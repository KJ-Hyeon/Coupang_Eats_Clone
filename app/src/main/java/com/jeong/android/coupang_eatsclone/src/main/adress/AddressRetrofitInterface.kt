package com.jeong.android.coupang_eatsclone.src.main.adress

import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressListResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.PostAddressRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AddressRetrofitInterface {

    @POST("/users/address")
    fun postAddress(@Body params: PostAddressRequest): Call<AddressResponse>

    @GET("/users/address")
    fun getAddressList(): Call<AddressListResponse>

}
