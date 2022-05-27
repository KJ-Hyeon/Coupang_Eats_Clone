package com.jeong.android.coupang_eatsclone.src.main.login

import com.jeong.android.coupang_eatsclone.src.main.join.models.SignUpResponse
import com.jeong.android.coupang_eatsclone.src.main.login.models.FindPasswordResponse
import com.jeong.android.coupang_eatsclone.src.main.login.models.LoginResponse

interface FindPasswordActivityInterface {

    fun onGetPasswordSuccess(response: FindPasswordResponse)

    fun onGetPasswordFailure(message: String)
}