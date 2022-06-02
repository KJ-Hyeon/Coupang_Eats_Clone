package com.jeong.android.coupang_eatsclone.src.main.page

import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressListResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.DetailAddressResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.PostLogoutResponse
import com.jeong.android.coupang_eatsclone.src.main.page.models.UserResponse

interface SettingInterface {

    fun onPostLogoutSuccess(response: PostLogoutResponse)

    fun onPostLogoutFailure(message: String)

}