package com.jeong.android.coupang_eatsclone.src.main.bookmark

import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.BookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.PatchBookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.PostBookMarkResponse
import retrofit2.Call
import retrofit2.http.*

interface BookMarkRetrofitInterface {



    @GET("/users/bookmarks")
    fun getBookMark() : Call<BookMarkResponse>

}
