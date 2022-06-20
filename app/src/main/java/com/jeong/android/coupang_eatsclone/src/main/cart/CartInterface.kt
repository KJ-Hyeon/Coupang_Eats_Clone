package com.jeong.android.coupang_eatsclone.src.main.cart

import com.jeong.android.coupang_eatsclone.src.main.cart.models.CartResponse
import com.jeong.android.coupang_eatsclone.src.main.cart.models.DeleteCartResponse
import com.jeong.android.coupang_eatsclone.src.main.cart.models.PatchCartResponse
import com.jeong.android.coupang_eatsclone.src.main.cart.models.PostCartResponse
import com.jeong.android.coupang_eatsclone.src.main.home.models.HomeStore

interface CartInterface {

    fun onGetCartSuccess(response: CartResponse)

    fun onGetCartFailure(message: String)

    fun onGetCartNull()

    fun onPatchCartSuccess(response: PatchCartResponse)

    fun onPatchCartFailure(message: String)

    fun onDeleteCartSuccess(response: DeleteCartResponse)

    fun onDeleteCartFailure(message: String)

    fun onPostCartSuccess(response: PostCartResponse)

    fun onPostCartFailure(message: String)

}