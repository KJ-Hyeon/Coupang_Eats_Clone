package com.jeong.android.coupang_eatsclone.src.main.cart.models

data class PostCartResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: String
)