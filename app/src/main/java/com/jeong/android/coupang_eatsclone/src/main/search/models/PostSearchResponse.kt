package com.jeong.android.coupang_eatsclone.src.main.search.models

data class PostSearchResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: PostSearchResult
)