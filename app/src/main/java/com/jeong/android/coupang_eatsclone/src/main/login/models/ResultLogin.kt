package com.jeong.android.coupang_eatsclone.src.main.login.models

import com.google.gson.annotations.SerializedName

data class ResultLogin(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("jwt") val jwt: String
)
