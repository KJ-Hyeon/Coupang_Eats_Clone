package com.jeong.android.coupang_eatsclone.src.main.order

import com.jeong.android.coupang_eatsclone.src.main.order.models.OrderResponse
import com.jeong.android.coupang_eatsclone.src.main.order.models.PostAddCartResponse

interface OrderInterface {

    fun onGetDetailMenuSuccess(response: OrderResponse)

    fun onGetDetailMenuFailure(message: String)

    fun onPostAddCartSuccess(response: PostAddCartResponse)

    fun onPostAddCartFailure(message: String)
}