package com.jeong.android.coupang_eatsclone.src.main.bookmark

import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.BookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.PatchBookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.PostBookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.home.models.HomeStore
import com.jeong.android.coupang_eatsclone.src.main.join.models.SignUpResponse

interface BookMarkInterface {

    fun onGetBookMarkSuccess(response: BookMarkResponse)

    fun onGetBookMarkFailure(message: String)


}