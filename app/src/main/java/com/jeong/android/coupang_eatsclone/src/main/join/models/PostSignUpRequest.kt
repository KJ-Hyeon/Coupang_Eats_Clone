package com.jeong.android.coupang_eatsclone.src.main.join.models

import com.google.gson.annotations.SerializedName

data class PostSignUpRequest(
    @SerializedName("user_email")val email: String,
    @SerializedName("user_password")val password: String,
    @SerializedName("user_name")val name: String,
    @SerializedName("user_phone")val phone: String
)
