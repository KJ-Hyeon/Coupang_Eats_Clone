package com.jeong.android.coupang_eatsclone.src.main.search.models

data class DeleteKeywordResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: String
)