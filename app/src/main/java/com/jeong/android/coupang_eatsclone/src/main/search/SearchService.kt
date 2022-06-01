package com.jeong.android.coupang_eatsclone.src.main.search

import android.content.ContentValues.TAG
import android.util.Log
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.src.main.order.models.OrderResponse
import com.jeong.android.coupang_eatsclone.src.main.order.models.PostAddCartRequest
import com.jeong.android.coupang_eatsclone.src.main.order.models.PostAddCartResponse
import com.jeong.android.coupang_eatsclone.src.main.search.models.DeleteKeywordResponse
import com.jeong.android.coupang_eatsclone.src.main.search.models.PostSearchRequest
import com.jeong.android.coupang_eatsclone.src.main.search.models.PostSearchResponse
import com.jeong.android.coupang_eatsclone.src.main.search.models.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class SearchService(val searchInterface: SearchInterface) {

    fun tryGetSearch(){
        val SearchRetrofitInterface = ApplicationClass.sRetrofit.create(SearchRetrofitInterface::class.java)
        SearchRetrofitInterface.getSearch().enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    searchInterface.onGetSearchSuccess(response.body() as SearchResponse)
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                searchInterface.onGetSearchFailure(t.message ?: "통신오류")
            }
        })
    }

    fun tryDeleteKeyword(searchIdx: Int) {
        val SearchRetrofitInterface = ApplicationClass.sRetrofit.create(SearchRetrofitInterface::class.java)
        SearchRetrofitInterface.deleteKeyword(searchIdx).enqueue(object : Callback<DeleteKeywordResponse> {
            override fun onResponse(
                call: Call<DeleteKeywordResponse>,
                response: Response<DeleteKeywordResponse>
            ) {
                if (response.isSuccessful) {
                    searchInterface.onDeleteKeywordSuccess(response.body() as DeleteKeywordResponse)
                }else {
                    Log.e(TAG, "onResponse: ${response.body()}", )
                }
            }

            override fun onFailure(call: Call<DeleteKeywordResponse>, t: Throwable) {
                searchInterface.onDeleteKeywordFailure(t.message ?: "통신오류")
            }
        })
    }

    fun tryDeleteAll() {
        val SearchRetrofitInterface = ApplicationClass.sRetrofit.create(SearchRetrofitInterface::class.java)
        SearchRetrofitInterface.deleteAll().enqueue(object : Callback<DeleteKeywordResponse> {
            override fun onResponse(
                call: Call<DeleteKeywordResponse>,
                response: Response<DeleteKeywordResponse>
            ) {
                if (response.isSuccessful) {
                    searchInterface.onDeleteAllSuccess(response.body() as DeleteKeywordResponse)
                }else {
                    Log.e(TAG, "onResponse: ${response.body()}", )
                }
            }

            override fun onFailure(call: Call<DeleteKeywordResponse>, t: Throwable) {
                searchInterface.onDeleteAllFailure(t.message ?: "통신오류")
            }
        })
    }
    
    fun tryPostSearch(postSearchRequest: PostSearchRequest) {
        val SearchRetrofitInterface = ApplicationClass.sRetrofit.create(SearchRetrofitInterface::class.java)
        SearchRetrofitInterface.postSearch(postSearchRequest).enqueue(object : Callback<PostSearchResponse> {
            override fun onResponse(
                call: Call<PostSearchResponse>,
                response: Response<PostSearchResponse>
            ) {
                if (response.isSuccessful) {
                    searchInterface.onPostSearchSuccess(response.body() as PostSearchResponse)
                } else {
                    Log.e(TAG, "onResponse: ${response.body()}", )
                }
            }

            override fun onFailure(call: Call<PostSearchResponse>, t: Throwable) {
                searchInterface.onPostSearchFailure(t.message ?: "통신오류")
            }
        })
    }

}