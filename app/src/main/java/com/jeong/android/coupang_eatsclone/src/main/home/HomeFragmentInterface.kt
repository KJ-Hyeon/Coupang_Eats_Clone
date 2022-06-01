package com.jeong.android.coupang_eatsclone.src.main.home

import com.jeong.android.coupang_eatsclone.src.main.cart.models.CartResponse
import com.jeong.android.coupang_eatsclone.src.main.home.models.HomeStore

interface HomeFragmentInterface {

    fun onGetStoreSuccess(response: HomeStore)

    fun onGetStoreFailure(message: String)

    fun onGetCartSuccess(response: CartResponse)

    fun onGetCartNull()

    fun onGetCartFailure(message: String)


}