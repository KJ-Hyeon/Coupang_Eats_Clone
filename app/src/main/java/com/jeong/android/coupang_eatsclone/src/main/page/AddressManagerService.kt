package com.jeong.android.coupang_eatsclone.src.main.page

import android.content.ContentValues
import android.util.Log
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.src.main.adress.AddressRetrofitInterface
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressListResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.DetailAddressResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class AddressManagerService(val addressManagerInterface: AddressManagerInterface) {

    fun tryGetAddressList(){
        val AddressRetrofitInterface = ApplicationClass.sRetrofit.create(AddressRetrofitInterface::class.java)
        AddressRetrofitInterface.getAddressList().enqueue(object : Callback<AddressListResponse>{
            override fun onResponse(
                call: Call<AddressListResponse>,
                response: Response<AddressListResponse>
            ) {
                if (response.isSuccessful) {
                    addressManagerInterface.onGetAddressSuccess(response.body() as AddressListResponse)
                }else {
                    Log.e(ContentValues.TAG, "onResponse:${response.code()},${response.message()}", )
                }
            }

            override fun onFailure(call: Call<AddressListResponse>, t: Throwable) {
                addressManagerInterface.onGetAddressFailure(t.message ?: "통신오류")
            }
        })
    }

    fun tryGetDetailAddress(index: Int) {
        val PageRetrofitInterface = ApplicationClass.sRetrofit.create(PageRetrofitInterface::class.java)
        PageRetrofitInterface.getDetailAddress(index).enqueue(object : Callback<DetailAddressResponse> {
            override fun onResponse(
                call: Call<DetailAddressResponse>,
                response: Response<DetailAddressResponse>
            ) {
                if (response.isSuccessful) {
                    addressManagerInterface.onGetDetailAddressSuccess(response.body() as DetailAddressResponse)
                }else {
                    Log.e(ContentValues.TAG, "onResponse:${response.code()},${response.message()}", )
                }
            }

            override fun onFailure(call: Call<DetailAddressResponse>, t: Throwable) {
                addressManagerInterface.onGetDetailAddressFailure(t.message ?: "통신오류")
            }
        })
    }



}
