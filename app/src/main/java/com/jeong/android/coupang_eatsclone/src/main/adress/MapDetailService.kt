package com.jeong.android.coupang_eatsclone.src.main.adress

import android.util.Log
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.PostAddressRequest
import com.jeong.android.coupang_eatsclone.src.main.page.models.AddressPatchRequest
import com.jeong.android.coupang_eatsclone.src.main.page.models.AddressPatchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

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

    fun tryDeleteAddress(index: Int) {
        val AddressRetrofitInterface = ApplicationClass.sRetrofit.create(AddressRetrofitInterface::class.java)
        AddressRetrofitInterface.deleteAddress(index).enqueue(object : Callback<AddressPatchResponse>{
            override fun onResponse(
                call: Call<AddressPatchResponse>,
                response: Response<AddressPatchResponse>
            ) {
                if (response.isSuccessful) {
                    mapDetailInterface.onDeleteAddressSuccess(response.body() as AddressPatchResponse)
                } else {
                    Log.e("TAG", "onResponse실패:${response.message()}", )
                }
            }

            override fun onFailure(call: Call<AddressPatchResponse>, t: Throwable) {
                mapDetailInterface.onDeleteAdressFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPatchAddress(addressPatchRequest: AddressPatchRequest, index: Int) {
        val AddressRetrofitInterface = ApplicationClass.sRetrofit.create(AddressRetrofitInterface::class.java)
        AddressRetrofitInterface.patchAddress(index,addressPatchRequest).enqueue(object : Callback<AddressPatchResponse>{
            override fun onResponse(
                call: Call<AddressPatchResponse>,
                response: Response<AddressPatchResponse>
            ) {
                if (response.isSuccessful) {
                    mapDetailInterface.onPatchAddressSuccess(response.body() as AddressPatchResponse)
                } else {
                    Log.e("TAG", "onResponse실패:${response.message()}", )
                }
            }

            override fun onFailure(call: Call<AddressPatchResponse>, t: Throwable) {
                mapDetailInterface.onPatchAdressFailure(t.message ?: "통신 오류")
            }
        })
    }

}
