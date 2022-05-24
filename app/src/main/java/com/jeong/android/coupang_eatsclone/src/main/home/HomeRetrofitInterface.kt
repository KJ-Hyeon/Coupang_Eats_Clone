package com.jeong.android.coupang_eatsclone.src.main.home

import com.jeong.android.coupang_eatsclone.src.main.home.models.HomeStore
import com.jeong.android.coupang_eatsclone.src.main.join.models.PostSignUpRequest
import com.jeong.android.coupang_eatsclone.src.main.join.models.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeRetrofitInterface {

    @GET("/store/home")
    fun getStores(): Call<HomeStore>

}
