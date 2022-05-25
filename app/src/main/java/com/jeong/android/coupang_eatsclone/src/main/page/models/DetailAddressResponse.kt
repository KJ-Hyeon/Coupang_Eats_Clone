package com.jeong.android.coupang_eatsclone.src.main.page.models

data class DetailAddressResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: DetailAddressResult
)