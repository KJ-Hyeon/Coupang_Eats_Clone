package com.jeong.android.coupang_eatsclone.src.main.join.models

import com.google.gson.annotations.SerializedName

data class ResultSignUp(
    @SerializedName("user_id") val userId: Int,
//    @SerializedName("jwt") val jwt: String
)
