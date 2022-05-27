package com.jeong.android.coupang_eatsclone.src.main.bookmark

import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.BookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.PatchBookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.PostBookMarkRequest
import com.jeong.android.coupang_eatsclone.src.main.home.models.HomeStore
import com.jeong.android.coupang_eatsclone.src.main.join.models.PostSignUpRequest
import com.jeong.android.coupang_eatsclone.src.main.join.models.SignUpResponse
import retrofit2.Call
import retrofit2.http.*

interface BookMarkRetrofitInterface {

    @POST("/users/bookmark/{storeIdx}")
    fun postBookMark(@Path("storeIdx") storeIdx: Int) : Call<PostBookMarkRequest>

    @PATCH("/users/bookmark/status/{storeIdx}")
    fun deleteBookMark(@Path("storeIdx") storeIdx: Int) : Call<PatchBookMarkResponse>

    @GET("/users/bookmark")
    fun getBookMark() : Call<BookMarkResponse>

}
