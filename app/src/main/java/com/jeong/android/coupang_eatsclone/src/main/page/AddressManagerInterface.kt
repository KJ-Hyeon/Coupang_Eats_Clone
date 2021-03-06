package com.jeong.android.coupang_eatsclone.src.main.page

import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressListResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.DetailAddressResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.UserResponse

interface AddressManagerInterface {

    fun onGetAddressSuccess(response: AddressListResponse)

    fun onGetAddressFailure(message: String)

    fun onGetDetailAddressSuccess(response: DetailAddressResponse)

    fun onGetDetailAddressFailure(message: String)


}