package com.jeong.android.coupang_eatsclone.src.main.search

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivitySearchKeywordBinding
import com.jeong.android.coupang_eatsclone.src.main.search.models.*

class SearchKeywordActivity : BaseActivity<ActivitySearchKeywordBinding>(ActivitySearchKeywordBinding::inflate),
            SearchInterface{

    private lateinit var popularText: ArrayList<TextView>
    private lateinit var searchRecyclerViewAdapter: SearchRecyclerViewAdapter
    private var searchList = mutableListOf<Search>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        popularText = arrayListOf(binding.tvPopularText1, binding.tvPopularText2, binding.tvPopularText3,
            binding.tvPopularText4, binding.tvPopularText5, binding.tvPopularText6, binding.tvPopularText7,
            binding.tvPopularText8, binding.tvPopularText9, binding.tvPopularText10)

        searchRecyclerViewAdapter = SearchRecyclerViewAdapter(searchList)
        binding.revSearch.adapter = searchRecyclerViewAdapter

        SearchService(this).tryGetSearch()

        binding.tvDeleteAll.setOnClickListener {
            SearchService(this).tryDeleteAll()
        }

        binding.icSearch.setOnClickListener {
            val editContent = binding.searchEdit.text.toString()
            if (!editContent.isNullOrEmpty()) {
                val postSearchRequest = PostSearchRequest(editContent)
                SearchService(this).tryPostSearch(postSearchRequest)
            }
        }

        searchRecyclerViewAdapter.setOnItemClickListener(object : SearchRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: Search) {
                revDeleteClick(data)
            }
        })
    }
    fun revDeleteClick(data: Search){
        SearchService(this).tryDeleteKeyword(data.search_id)
    }


    override fun onGetSearchSuccess(response: SearchResponse) {
        binding.tvUpdateTime.text = response.result.updated_time
        binding.tvRecentKeyword.visibility = View.VISIBLE
        for (i in response.result.popularSearchList.indices){
            popularText[i].text = response.result.popularSearchList[i].category_name
        }
        if (response.result.searchList.isEmpty()) {
            binding.tvRecentKeyword.visibility = View.INVISIBLE
            binding.tvDeleteAll.visibility = View.INVISIBLE
        } else {
            binding.tvRecentKeyword.visibility = View.VISIBLE
            binding.tvDeleteAll.visibility = View.VISIBLE
        }
        searchRecyclerViewAdapter.addData(response.result.searchList)
    }

    override fun onGetSearchFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onDeleteKeywordSuccess(response: DeleteKeywordResponse) {
        searchRecyclerViewAdapter.clearData()
        SearchService(this).tryGetSearch()
    }

    override fun onDeleteKeywordFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onDeleteAllSuccess(response: DeleteKeywordResponse) {
        searchRecyclerViewAdapter.clearData()
        binding.tvDeleteAll.visibility = View.INVISIBLE
        binding.tvRecentKeyword.visibility = View.INVISIBLE
    }

    override fun onDeleteAllFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onPostSearchSuccess(response: PostSearchResponse) {
        //화면이동
        searchRecyclerViewAdapter.clearData()
        SearchService(this).tryGetSearch()
    }

    override fun onPostSearchFailure(message: String) {
        TODO("Not yet implemented")
    }
}

// TODO: 전체 삭제 는 되고 반영이 안됨 반영만 하면 됨 .