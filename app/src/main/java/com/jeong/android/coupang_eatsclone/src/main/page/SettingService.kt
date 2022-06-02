package com.jeong.android.coupang_eatsclone.src.main.page

import android.content.ContentValues.TAG
import android.util.Log
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.PostLogoutResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingService(val settingInterface: SettingInterface) {

    fun tryPostLogout(){
        val PageRetrofitInterface = ApplicationClass.sRetrofit.create(PageRetrofitInterface::class.java)
        PageRetrofitInterface.postLogout().enqueue(object : Callback<PostLogoutResponse>{
            override fun onResponse(
                call: Call<PostLogoutResponse>,
                response: Response<PostLogoutResponse>
            ) {
                if (response.isSuccessful) {
                    settingInterface.onPostLogoutSuccess(response.body() as PostLogoutResponse)
                }else {
                    Log.e(TAG, "onResponse: ${response.body()}", )
                }
            }

            override fun onFailure(call: Call<PostLogoutResponse>, t: Throwable) {
                settingInterface.onPostLogoutFailure(t.message ?: "통신오류")
            }
        })
    }

}
