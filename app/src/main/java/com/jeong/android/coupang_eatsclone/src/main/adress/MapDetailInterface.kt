package com.jeong.android.coupang_eatsclone.src.main.adress

import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.AddressPatchResponse

interface MapDetailInterface {

    fun onPostAddressSuccess(response: AddressResponse)

    fun onPostAdressFailure(message: String)

    fun onDeleteAddressSuccess(response: AddressPatchResponse)

    fun onDeleteAdressFailure(message: String)

    fun onPatchAddressSuccess(response: AddressPatchResponse)

    fun onPatchAdressFailure(message: String)
}