package com.jeong.android.coupang_eatsclone.src.main.cart.models

data class CartMenu(
    val cart_id: Int,
    val menu_name: String,
    val menu_option_id: Int,
    val option_name: String,
    val option_price: Int,
    val price: Int
)