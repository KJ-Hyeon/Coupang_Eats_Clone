package com.jeong.android.coupang_eatsclone.src.main.order

data class OrderResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: OrderResult
)