package com.jeong.android.coupang_eatsclone.src.main.login.models

import com.google.gson.annotations.SerializedName
import com.jeong.android.coupang_eatsclone.src.main.join.models.ResultSignUp

data class LoginResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ResultSignUp
)
