package com.jeong.android.coupang_eatsclone.src.main.bookmark.models

data class PostBookMarkRequest(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)