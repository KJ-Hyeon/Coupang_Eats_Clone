package com.jeong.android.coupang_eatsclone.src.main.search

import com.jeong.android.coupang_eatsclone.src.main.order.models.OrderResponse
import com.jeong.android.coupang_eatsclone.src.main.order.models.PostAddCartResponse
import com.jeong.android.coupang_eatsclone.src.main.search.models.DeleteKeywordResponse
import com.jeong.android.coupang_eatsclone.src.main.search.models.PostSearchResponse
import com.jeong.android.coupang_eatsclone.src.main.search.models.SearchResponse

interface SearchInterface {

    fun onGetSearchSuccess(response: SearchResponse)
    
    fun onGetSearchFailure(message: String)

    fun onDeleteKeywordSuccess(response: DeleteKeywordResponse)

    fun onDeleteKeywordFailure(message: String)

    fun onDeleteAllSuccess(response: DeleteKeywordResponse)

    fun onDeleteAllFailure(message: String)

    fun onPostSearchSuccess(response: PostSearchResponse)

    fun onPostSearchFailure(message: String)

}