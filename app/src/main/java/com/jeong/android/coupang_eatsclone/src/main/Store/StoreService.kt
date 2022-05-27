package com.jeong.android.coupang_eatsclone.src.main.Store

import android.content.ContentValues.TAG
import android.util.Log
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.src.main.Store.models.StoreResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreService(val StoreInterface: StoreInterface) {

    fun tryGetStore(index: Int){
        val StoreRetrofitInterface = ApplicationClass.sRetrofit.create(StoreRetrofitInterface::class.java)
        StoreRetrofitInterface.getStore(index).enqueue(object : Callback<StoreResponse>{
            override fun onResponse(call: Call<StoreResponse>, response: Response<StoreResponse>) {
                if (response.isSuccessful) {
                    StoreInterface.onGetStoreSuccess(response.body() as StoreResponse)
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}", )
                }
            }

            override fun onFailure(call: Call<StoreResponse>, t: Throwable) {
                StoreInterface.onGetStoreFailure(t.message ?: "통신오류")
            }
        })
    }

}
