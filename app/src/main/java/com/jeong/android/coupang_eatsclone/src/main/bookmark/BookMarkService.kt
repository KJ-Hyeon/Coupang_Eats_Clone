package com.jeong.android.coupang_eatsclone.src.main.bookmark

import android.content.ContentValues.TAG
import android.util.Log
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.BookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.PatchBookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.bookmark.models.PostBookMarkResponse
import com.jeong.android.coupang_eatsclone.src.main.home.models.HomeStore
import com.jeong.android.coupang_eatsclone.src.main.join.models.PostSignUpRequest
import com.jeong.android.coupang_eatsclone.src.main.join.models.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class BookMarkService(val bookMarkInterface: BookMarkInterface) {

    fun tryGetBookmark(){
        val BookMarkRetrofitInterface = ApplicationClass.sRetrofit.create(BookMarkRetrofitInterface::class.java)
        BookMarkRetrofitInterface.getBookMark().enqueue(object : Callback<BookMarkResponse>{
            override fun onResponse(
                call: Call<BookMarkResponse>,
                response: Response<BookMarkResponse>
            ) {
                if (response.isSuccessful) {
                    bookMarkInterface.onGetBookMarkSuccess(response.body() as BookMarkResponse)
                }else {
                    Log.e(TAG, "onResponse: ${response.message()}", )
                }
            }

            override fun onFailure(call: Call<BookMarkResponse>, t: Throwable) {
                bookMarkInterface.onGetBookMarkFailure(t.message ?: "통신오류")
            }
        })
    }



}