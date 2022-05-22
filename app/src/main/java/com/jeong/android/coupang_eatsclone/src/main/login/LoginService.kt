package com.jeong.android.coupang_eatsclone.src.main.login

import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.src.main.login.models.LoginResponse
import com.jeong.android.coupang_eatsclone.src.main.login.models.PostLoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val loginActivityInterface: LoginActivityInterface) {

    fun tryPostLogin(postLoginRequest: PostLoginRequest) {
        val LoginRetrofit = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        LoginRetrofit.postLogin(postLoginRequest).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                loginActivityInterface.onPostLoginSuccess(response.body() as LoginResponse)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginActivityInterface.onPostLoginFailure(t.message ?: "통신오류")
            }
        })
    }
}