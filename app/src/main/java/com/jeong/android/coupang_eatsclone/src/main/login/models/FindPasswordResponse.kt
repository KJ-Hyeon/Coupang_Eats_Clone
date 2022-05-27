package com.jeong.android.coupang_eatsclone.src.main.login.models

data class FindPasswordResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val resultFindPassword: ResultFindPassword
)