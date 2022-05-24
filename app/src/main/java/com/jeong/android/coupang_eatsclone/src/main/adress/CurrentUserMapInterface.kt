package com.jeong.android.coupang_eatsclone.src.main.adress

import com.jeong.android.coupang_eatsclone.src.main.adress.kakaomodels.KaKaoData

interface CurrentUserMapInterface {

    fun onGetAddresSuccess(response: KaKaoData)

    fun onGetAdressFailure(message: String)
}