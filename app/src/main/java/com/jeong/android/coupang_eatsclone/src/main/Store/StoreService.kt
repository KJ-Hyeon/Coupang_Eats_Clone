package com.jeong.android.coupang_eatsclone.src.main.Store

import android.content.ContentValues.TAG
import android.util.Log
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.src.main.Store.models.StoreResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.BookMarkRetrofitInterface
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.PatchBookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.PostBookMarkResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreService(val StoreInterface: StoreInterface) {

    fun tryGetStore(index: Int){
        val StoreRetrofitInterface = ApplicationClass.sRetrofit.create(StoreRetrofitInterface::class.java)
        StoreRetrofitInterface.getStore(index).enqueue(object : Callback<StoreResponse>{
            override fun onResponse(call: Call<StoreResponse>, response: Response<StoreResponse>) {
                if (response.isSuccessful) {
                    StoreInterface.onGetStoreSuccess(response.body() as StoreResponse)
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}", )
                }
            }

            override fun onFailure(call: Call<StoreResponse>, t: Throwable) {
                StoreInterface.onGetStoreFailure(t.message ?: "통신오류")
            }
        })
    }

    fun tryPostBookmark(index : Int){
        val StoreRetrofitInterface = ApplicationClass.sRetrofit.create(StoreRetrofitInterface::class.java)
        StoreRetrofitInterface.postBookMark(index).enqueue(object : Callback<PostBookMarkResponse>{
            override fun onResponse(
                call: Call<PostBookMarkResponse>,
                response: Response<PostBookMarkResponse>
            ) {
                if (response.isSuccessful) {
                    StoreInterface.onPostBookMarkSuccess(response.body() as PostBookMarkResponse)
                }else {
                    Log.e(TAG, "onResponse: ${response.message()}", )
                }
            }

            override fun onFailure(call: Call<PostBookMarkResponse>, t: Throwable) {
                StoreInterface.onPostBookMarkFailure(t.message ?: "통신오류")
            }
        })
    }

    fun tryDeleteBookmark(index : Int){
        val StoreRetrofitInterface = ApplicationClass.sRetrofit.create(StoreRetrofitInterface::class.java)
        StoreRetrofitInterface.deleteBookMark(index).enqueue(object : Callback<PatchBookMarkResponse>{
            override fun onResponse(
                call: Call<PatchBookMarkResponse>,
                response: Response<PatchBookMarkResponse>
            ) {
                if (response.isSuccessful) {
                    StoreInterface.onDeleteBookMarkSuccess(response.body() as PatchBookMarkResponse)
                }else {
                    Log.e(TAG, "onResponse: ${response.message()}", )
                }
            }

            override fun onFailure(call: Call<PatchBookMarkResponse>, t: Throwable) {
                StoreInterface.onDeleteBookMarkFailure(t.message ?: "통신오류")
            }
        })
    }

}
