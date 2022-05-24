package com.jeong.android.coupang_eatsclone.src.main.adress.models

data class PostAddress(
    var main_address: String,
    var detail_address: String,
    var address_guide: String? = null,
    var user_id: Int,
    var address_longitude : Double,
    var address_latitude: Double,
    var address_name : String
)
