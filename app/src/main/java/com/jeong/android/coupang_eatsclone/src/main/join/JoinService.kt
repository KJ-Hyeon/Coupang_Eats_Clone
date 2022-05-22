package com.jeong.android.coupang_eatsclone.src.main.join

import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.src.main.join.models.PostSignUpRequest
import com.jeong.android.coupang_eatsclone.src.main.join.models.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinService(val joinActivityInterface: JoinActivityInterface) {

    fun tryPostSignUp(postSignUpRequest: PostSignUpRequest){
        val JoinRetrofitInterface = ApplicationClass.sRetrofit.create(JoinRetrofitInterface::class.java)
        JoinRetrofitInterface.postSignUp(postSignUpRequest).enqueue(object : Callback<SignUpResponse>{
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                joinActivityInterface.onPostSignUpSuccess(response.body() as SignUpResponse)
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                joinActivityInterface.onPostSignUpFailure(t.message ?: "통신오류")
            }
        })
    }

}