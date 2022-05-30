package com.jeong.android.coupang_eatsclone.src.main.order.models

data class OrderResult(
    val menu_img_url: String,
    val menu_name: String,
    val menu_option: List<MenuOption>,
    val menu_price: Int
)