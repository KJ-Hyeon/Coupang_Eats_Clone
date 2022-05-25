package com.jeong.android.coupang_eatsclone.src.main.adress.models

data class PostAddressRequest(
    var main_address: String,
    var detail_address: String? = null,
    var address_guide: String? = null,
    var user_id: Int,
    var longitude : Double,
    var latitude: Double,
    var address_name : String,
    var status : String
)
