package com.jeong.android.coupang_eatsclone.src.main.order

import com.jeong.android.coupang_eatsclone.src.main.home.models.HomeStore
import com.jeong.android.coupang_eatsclone.src.main.join.models.SignUpResponse

interface OrderInterface {

    fun onGetDetailMenuSuccess(response: HomeStore)

    fun onGetDetailMenuFailure(message: String)
}