package com.jeong.android.coupang_eatsclone.src.main.adress.kakaonetwork

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object kakaoRetrofitBuilder {
    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(KaKaoApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
    val kakaoapi : KaKaoApiService by lazy {
        retrofit
            .build()
            .create(KaKaoApiService::class.java)
    }
}