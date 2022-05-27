package com.jeong.android.coupang_eatsclone.src.main.Store.models

data class StoreResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: StoreResult
)