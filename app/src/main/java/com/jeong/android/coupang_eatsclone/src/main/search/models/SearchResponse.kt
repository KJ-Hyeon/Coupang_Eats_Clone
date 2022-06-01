package com.jeong.android.coupang_eatsclone.src.main.search.models

data class SearchResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)