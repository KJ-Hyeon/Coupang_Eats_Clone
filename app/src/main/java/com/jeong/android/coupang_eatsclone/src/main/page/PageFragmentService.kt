package com.jeong.android.coupang_eatsclone.src.main.page

import android.util.Log
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PageFragmentService(val pageFragmentInterface: PageFragmentInterface) {

    fun tryGetUser(){
        val PageRetrofitInterface = ApplicationClass.sRetrofit.create(PageRetrofitInterface::class.java)
        PageRetrofitInterface.getUser().enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    pageFragmentInterface.onGetuserSuccess(response.body() as UserResponse)
                    Log.e("TAG", "onResponse:${response.body()}", )
                }else {
                    Log.e("TAG", "onResponse:${response.message()}", )
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                pageFragmentInterface.onGetuserFailure(t.message ?: "통신오류")
            }
        })
    }

}
