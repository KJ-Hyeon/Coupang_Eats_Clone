package com.jeong.android.coupang_eatsclone.src.main.cart.models

data class CartResult(
    val cartMenu: List<CartMenu>,
    val is_cheetah_delivery: String,
    val store_id: Int,
    val store_name: String
)