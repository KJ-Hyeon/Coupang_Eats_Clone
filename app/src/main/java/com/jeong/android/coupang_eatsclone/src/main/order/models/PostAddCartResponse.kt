package com.jeong.android.coupang_eatsclone.src.main.order.models

data class PostAddCartResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: String
)