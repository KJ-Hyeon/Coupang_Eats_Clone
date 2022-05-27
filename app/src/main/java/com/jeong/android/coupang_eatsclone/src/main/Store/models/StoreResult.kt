package com.jeong.android.coupang_eatsclone.src.main.Store.models

data class StoreResult(
    val average: Double,
    val cnt: Int,
    val delivery_time: String,
    val is_cheetah_delivery: String,
    val minimum_price: Int,
    val review: List<Review>,
    val start_delivery_fee: Int,
    val store_main_image_url: String,
    val store_name: String
)