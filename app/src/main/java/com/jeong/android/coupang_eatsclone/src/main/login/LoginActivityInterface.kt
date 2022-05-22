package com.jeong.android.coupang_eatsclone.src.main.login

import com.jeong.android.coupang_eatsclone.src.main.join.models.SignUpResponse
import com.jeong.android.coupang_eatsclone.src.main.login.models.LoginResponse

interface LoginActivityInterface {

    fun onPostLoginSuccess(response: LoginResponse)

    fun onPostLoginFailure(message: String)
}