package com.jeong.android.coupang_eatsclone.src.main.login

import com.jeong.android.coupang_eatsclone.src.main.login.models.FindPasswordResponse
import com.jeong.android.coupang_eatsclone.src.main.login.models.LoginResponse
import com.jeong.android.coupang_eatsclone.src.main.login.models.PostLoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginRetrofitInterface {
    @POST("/users/login")
    fun postLogin(@Body params: PostLoginRequest): Call<LoginResponse>

    @GET("users/password")
    fun getPassword(
        @Query ("user_name") user_name: String,
        @Query ("user_email") user_email: String
    ): Call<FindPasswordResponse>

}