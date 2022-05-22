package com.jeong.android.coupang_eatsclone.src.main.join

import com.jeong.android.coupang_eatsclone.src.main.join.models.PostSignUpRequest
import com.jeong.android.coupang_eatsclone.src.main.join.models.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface JoinRetrofitInterface {

    @POST("/users/join")
    fun postSignUp(@Body params: PostSignUpRequest): Call<SignUpResponse>

}
