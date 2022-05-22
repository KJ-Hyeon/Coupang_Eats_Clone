package com.jeong.android.coupang_eatsclone.src.main.login

import com.jeong.android.coupang_eatsclone.src.main.join.models.PostSignUpRequest
import com.jeong.android.coupang_eatsclone.src.main.join.models.SignUpResponse
import com.jeong.android.coupang_eatsclone.src.main.login.models.LoginResponse
import com.jeong.android.coupang_eatsclone.src.main.login.models.PostLoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitInterface {
    @POST("/users/login")
    fun postLogin(@Body params: PostLoginRequest): Call<LoginResponse>

}