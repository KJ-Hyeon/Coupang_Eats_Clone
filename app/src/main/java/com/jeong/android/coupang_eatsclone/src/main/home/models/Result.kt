package com.jeong.android.coupang_eatsclone.src.main.home.models

data class Result(
    val delivery_time: String,
    val is_cheetah_delivery: String,
    val ravg: Double,
    val rcnt: Int,
    val store_main_image_url: String,
    val store_name: String,
    val take_out: String
)