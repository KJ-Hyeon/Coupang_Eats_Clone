package com.jeong.android.coupang_eatsclone.src.main.page

import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressListResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.UserResponse

interface PageFragmentInterface {

    fun onGetuserSuccess(response: UserResponse)

    fun onGetuserFailure(message: String)

//    fun onGetAddressSuccess(response: AddressListResponse)
//
//    fun onGetAddressFailure(message: String)


}