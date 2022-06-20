package com.jeong.android.coupang_eatsclone.src.main.cart.models

import com.google.android.datatransport.runtime.dagger.multibindings.IntoMap

data class PostCartRequest(
    val address_id : Int,
    val store_id : Int,
    val payment_method_id : Int,
    val delivery_request : Int,
    val store_request : String
)