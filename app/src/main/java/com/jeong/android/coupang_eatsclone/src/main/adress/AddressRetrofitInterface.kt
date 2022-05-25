package com.jeong.android.coupang_eatsclone.src.main.adress

import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressListResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.PostAddressRequest
import com.jeong.android.coupang_eatsclone.src.main.page.models.AddressPatchRequest
import com.jeong.android.coupang_eatsclone.src.main.page.models.AddressPatchResponse
import retrofit2.Call
import retrofit2.http.*

interface AddressRetrofitInterface {

    @POST("/users/address")
    fun postAddress(@Body params: PostAddressRequest): Call<AddressResponse>

    @GET("/users/address")
    fun getAddressList(): Call<AddressListResponse>

    // 주소 수정
    @PATCH("/users/address/{addressIdx}")
    fun patchAddress(@Path("addressIdx") address: Int, @Body params: AddressPatchRequest) : Call<AddressPatchResponse>

    // 주소 삭제
    @PATCH("/users/address/status/{addressIdx}")
    fun deleteAddress(@Path("addressIdx") addressIdx: Int): Call<AddressPatchResponse>

}
