package com.jeong.android.coupang_eatsclone.src.main.bookmark.models

data class ResultBookMarkResponse(
    val bookmark_count: Int,
    val reviewSimpleList: List<ReviewSimple>,
    val storeSimpleList: List<StoreSimple>
)