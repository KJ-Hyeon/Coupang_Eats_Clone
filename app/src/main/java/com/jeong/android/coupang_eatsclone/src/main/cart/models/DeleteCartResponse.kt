package com.jeong.android.coupang_eatsclone.src.main.cart.models

data class DeleteCartResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: String
)