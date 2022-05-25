package com.jeong.android.coupang_eatsclone.src.main.page.models

import com.jeong.android.coupang_eatsclone.src.main.page.models.Result

data class UserResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)