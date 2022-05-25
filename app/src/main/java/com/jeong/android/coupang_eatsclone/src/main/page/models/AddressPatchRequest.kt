package com.jeong.android.coupang_eatsclone.src.main.page.models

data class AddressPatchRequest(
    var detail_address: String? = null,
    var address_guide: String? = null,
    var user_id: Int,
    var longitude : Double,
    var latitude: Double,
    var address_name : String,
    var status : String
)
