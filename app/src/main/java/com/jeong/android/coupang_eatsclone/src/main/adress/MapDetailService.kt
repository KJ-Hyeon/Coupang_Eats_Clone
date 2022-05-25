package com.jeong.android.coupang_eatsclone.src.main.adress

import android.util.Log
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.PostAddressRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapDetailService(val mapDetailInterface: MapDetailInterface) {

    fun tryPostAddress(postAddressRequest: PostAddressRequest){
        val AddressRetrofitInterface = ApplicationClass.sRetrofit.create(AddressRetrofitInterface::class.java)
        AddressRetrofitInterface.postAddress(postAddressRequest).enqueue(object : Callback<AddressResponse>{
            override fun onResponse(
                call: Call<AddressResponse>,
                response: Response<AddressResponse>
            ) {
                if (response.isSuccessful) {
                    mapDetailInterface.onPostAddressSuccess(response.body() as AddressResponse)
                } else {
                    Log.e("TAG", "onResponse실패:${response.message()}", )
                }
            }

            override fun onFailure(call: Call<AddressResponse>, t: Throwable) {
                mapDetailInterface.onPostAdressFailure(t.message ?: "통신 오류")
            }
        })
    }

}
