package com.jeong.android.coupang_eatsclone.src.main.bookmark.models

data class PatchBookMarkResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: String
)