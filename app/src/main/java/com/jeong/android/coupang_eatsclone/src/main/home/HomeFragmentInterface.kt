package com.jeong.android.coupang_eatsclone.src.main.home

import com.jeong.android.coupang_eatsclone.src.main.home.models.HomeStore
import com.jeong.android.coupang_eatsclone.src.main.join.models.SignUpResponse

interface HomeFragmentInterface {

    fun onGetStoreSuccess(response: HomeStore)

    fun onGetStoreFailure(message: String)
}