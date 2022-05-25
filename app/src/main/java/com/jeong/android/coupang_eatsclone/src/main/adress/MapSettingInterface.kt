package com.jeong.android.coupang_eatsclone.src.main.adress

import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressListResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse

interface MapSettingInterface {

    fun onGetAddressSuccess(response: AddressListResponse)

    fun onGetAddressFailure(message: String)
}