package com.jeong.android.coupang_eatsclone.src.main.Store

import com.jeong.android.coupang_eatsclone.src.main.Store.models.StoreResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.PatchBookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.PostBookMarkResponse

interface StoreInterface {

    fun onGetStoreSuccess(response: StoreResponse)

    fun onGetStoreFailure(message: String)

    fun onPostBookMarkSuccess(response: PostBookMarkResponse)

    fun onPostBookMarkFailure(message: String)

    fun onDeleteBookMarkSuccess(response: PatchBookMarkResponse)

    fun onDeleteBookMarkFailure(message: String)

}