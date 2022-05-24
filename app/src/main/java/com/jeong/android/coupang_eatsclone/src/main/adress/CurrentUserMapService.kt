package com.jeong.android.coupang_eatsclone.src.main.adress

import android.util.Log
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.src.main.adress.kakaomodels.KaKaoData
import com.jeong.android.coupang_eatsclone.src.main.adress.kakaonetwork.KaKaoApiService
import com.jeong.android.coupang_eatsclone.src.main.adress.kakaonetwork.kakaoRetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentUserMapService(val currentUserMapInterface: CurrentUserMapInterface) {

    val API_KEY = "KakaoAK c3e7879246b274d1ced486798b6ec15c"
    private val KaKaoApi = kakaoRetrofitBuilder.kakaoapi

    fun tryGetAdress(x:String, y:String) {
        KaKaoApi.getKaKaoAdress(API_KEY, x, y)
            .enqueue(object : Callback<KaKaoData>{
                override fun onResponse(call: Call<KaKaoData>, response: Response<KaKaoData>) {
                    if (response.isSuccessful) {
                        currentUserMapInterface.onGetAddresSuccess(response.body() as KaKaoData)
                    } else{
                        Log.e("TAG", "onResponse:${response.message()},${response.code()} ", )
                    }
                }

                override fun onFailure(call: Call<KaKaoData>, t: Throwable) {
                    currentUserMapInterface.onGetAdressFailure(t.message ?: "통신 오류")
                }
            })
    }
}
