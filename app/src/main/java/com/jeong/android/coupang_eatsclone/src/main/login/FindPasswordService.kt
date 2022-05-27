package com.jeong.android.coupang_eatsclone.src.main.login

import android.util.Log
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.src.main.login.models.FindPasswordResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindPasswordService(val findPasswordActivityInterface: FindPasswordActivityInterface) {

    fun tryGetPassword(user_name : String, user_email : String ) {
        val LoginRetrofit = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        LoginRetrofit.getPassword(user_name, user_email).enqueue(object : Callback<FindPasswordResponse>{
            override fun onResponse(
                call: Call<FindPasswordResponse>,
                response: Response<FindPasswordResponse>
            ) {
                if (response.isSuccessful) {
                    findPasswordActivityInterface.onGetPasswordSuccess(response.body() as FindPasswordResponse)
                }else {
                    Log.e("TAG", "onResponse:${response.code()}", )
                }
            }

            override fun onFailure(call: Call<FindPasswordResponse>, t: Throwable) {
                findPasswordActivityInterface.onGetPasswordFailure(t.message ?: "통신오류")
            }
        })
    }
}