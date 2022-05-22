package com.jeong.android.coupang_eatsclone.src.main.login.models

import com.google.gson.annotations.SerializedName

data class PostLoginRequest(
    @SerializedName("user_email")val email: String,
    @SerializedName("user_password")val password: String,
)
