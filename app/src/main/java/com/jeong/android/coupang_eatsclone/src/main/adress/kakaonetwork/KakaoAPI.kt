package com.jeong.android.coupang_eatsclone.src.main.adress.kakaonetwork

import com.jeong.android.coupang_eatsclone.src.main.adress.kakaomodels.KaKaoData
import retrofit2.Call
import retrofit2.http.*

class KaKaoApi {
    companion object {
        const val BASE_URL = "https://dapi.kakao.com/"
    }
}
interface KaKaoApiService {
    @GET("/v2/local/geo/coord2address.json")
    fun getKaKaoAdress(
        @Header("Authorization") key: String,
        @Query("x") x: String,
        @Query("y") y: String,
    ):Call<KaKaoData>
}
