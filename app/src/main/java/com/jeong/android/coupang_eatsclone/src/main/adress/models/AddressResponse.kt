package com.jeong.android.coupang_eatsclone.src.main.adress.models

data class AddressResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)