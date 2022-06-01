package com.jeong.android.coupang_eatsclone.src.main.search.models

data class Result(
    val popularSearchList: List<PopularSearch>,
    val searchList: List<Search>,
    val updated_time: String
)