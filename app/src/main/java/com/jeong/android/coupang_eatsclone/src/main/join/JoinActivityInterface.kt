package com.jeong.android.coupang_eatsclone.src.main.join

import com.jeong.android.coupang_eatsclone.src.main.join.models.SignUpResponse

interface JoinActivityInterface {

    fun onPostSignUpSuccess(response: SignUpResponse)

    fun onPostSignUpFailure(message: String)
}