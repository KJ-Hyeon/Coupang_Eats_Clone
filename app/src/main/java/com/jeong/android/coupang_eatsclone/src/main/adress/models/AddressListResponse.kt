package com.jeong.android.coupang_eatsclone.src.main.adress.models

data class AddressListResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<ResultAddressList>
)