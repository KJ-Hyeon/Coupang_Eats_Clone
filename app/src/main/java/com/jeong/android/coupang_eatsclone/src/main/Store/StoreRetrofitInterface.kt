package com.jeong.android.coupang_eatsclone.src.main.Store

import com.jeong.android.coupang_eatsclone.src.main.Store.models.StoreResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.PatchBookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.PostBookMarkResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface StoreRetrofitInterface {

    @GET("/store/{storeIdx}")
    fun getStore(@Path("storeIdx") storeIdx: Int): Call<StoreResponse>

    @POST("/users/bookmark/{storeIdx}")
    fun postBookMark(@Path("storeIdx") storeIdx: Int) : Call<PostBookMarkResponse>

    @PATCH("/users/bookmark/status/{storeIdx}")
    fun deleteBookMark(@Path("storeIdx") storeIdx: Int) : Call<PatchBookMarkResponse>
}
