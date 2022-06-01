package com.jeong.android.coupang_eatsclone.src.main.search

import com.jeong.android.coupang_eatsclone.src.main.order.models.OrderResponse
import com.jeong.android.coupang_eatsclone.src.main.order.models.PostAddCartRequest
import com.jeong.android.coupang_eatsclone.src.main.order.models.PostAddCartResponse
import com.jeong.android.coupang_eatsclone.src.main.search.models.DeleteKeywordResponse
import com.jeong.android.coupang_eatsclone.src.main.search.models.PostSearchRequest
import com.jeong.android.coupang_eatsclone.src.main.search.models.PostSearchResponse
import com.jeong.android.coupang_eatsclone.src.main.search.models.SearchResponse
import retrofit2.Call
import retrofit2.http.*

interface SearchRetrofitInterface {

    @GET("/categories/search")
    fun getSearch() : Call<SearchResponse>

    @PATCH("/categories/search/{searchIdx}/status")
    fun deleteKeyword(
        @Path("searchIdx") searchIdx : Int
    ) : Call<DeleteKeywordResponse>

    @PATCH("/categories/search/status")
    fun deleteAll() : Call<DeleteKeywordResponse>

    @POST("/categories/search")
    fun postSearch(
        @Body params: PostSearchRequest
    ) :Call<PostSearchResponse>

}
