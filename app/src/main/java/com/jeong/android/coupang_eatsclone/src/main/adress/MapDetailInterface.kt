package com.jeong.android.coupang_eatsclone.src.main.adress

import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse

interface MapDetailInterface {

    fun onPostAddressSuccess(response: AddressResponse)

    fun onPostAdressFailure(message: String)
}