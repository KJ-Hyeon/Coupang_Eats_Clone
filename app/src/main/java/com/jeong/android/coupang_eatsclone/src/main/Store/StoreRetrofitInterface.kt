package com.jeong.android.coupang_eatsclone.src.main.Store

import com.jeong.android.coupang_eatsclone.src.main.Store.models.StoreResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressListResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.AddressPatchRequest
import com.jeong.android.coupang_eatsclone.src.main.page.models.AddressPatchResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.DetailAddressResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface StoreRetrofitInterface {

    @GET("/store/{storeIdx}")
    fun getStore(@Path("storeIdx") storeIdx: Int): Call<StoreResponse>
}
