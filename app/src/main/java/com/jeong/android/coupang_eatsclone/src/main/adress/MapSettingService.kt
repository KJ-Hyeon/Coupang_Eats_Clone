package com.jeong.android.coupang_eatsclone.src.main.adress

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressListResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.PostAddressRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapSettingService(val mapSettingInterface: MapSettingInterface) {

    fun tryGetAddressList(){
        val AddressRetrofitInterface = ApplicationClass.sRetrofit.create(AddressRetrofitInterface::class.java)

        AddressRetrofitInterface.getAddressList().enqueue(object : Callback<AddressListResponse>{
            override fun onResponse(
                call: Call<AddressListResponse>,
                response: Response<AddressListResponse>
            ) {
                if (response.isSuccessful) {
                    mapSettingInterface.onGetAddressSuccess(response.body() as AddressListResponse)
                }else {
                    Log.e(ContentValues.TAG, "onResponse:${response.code()},${response.message()}", )
                }
            }

            override fun onFailure(call: Call<AddressListResponse>, t: Throwable) {
                mapSettingInterface.onGetAddressFailure(t.message ?: "통신오류")
            }
        })
    }

}
