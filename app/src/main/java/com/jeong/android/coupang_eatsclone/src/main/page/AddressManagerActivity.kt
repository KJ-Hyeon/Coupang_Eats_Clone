package com.jeong.android.coupang_eatsclone.src.main.page

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityAddressManagerBinding
import com.jeong.android.coupang_eatsclone.src.main.adress.AddressRecyclerViewAdapter
import com.jeong.android.coupang_eatsclone.src.main.adress.MapDetailActivity
import com.jeong.android.coupang_eatsclone.src.main.adress.MapSettingActivity
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressListResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.ResultAddressList
import com.jeong.android.coupang_eatsclone.src.main.home.HomeRecyclerViewAdapter
import com.jeong.android.coupang_eatsclone.src.main.page.models.DetailAddressResponse

class AddressManagerActivity : BaseActivity<ActivityAddressManagerBinding>(ActivityAddressManagerBinding::inflate),
            AddressManagerInterface{

    private lateinit var addressRecyclerViewAdapter: AddressRecyclerViewAdapter
    private var addressList = mutableListOf<ResultAddressList>()

    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        AddressManagerService(this).tryGetAddressList()
        showLoadingDialog(this)
        val jwtToken: String? = ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN, null)
        Log.e(TAG, "onCreate: ${jwtToken}", )


        addressRecyclerViewAdapter = AddressRecyclerViewAdapter(addressList)
        binding.managerRev.adapter = addressRecyclerViewAdapter

        binding.tvAddAddress.setOnClickListener {
            val intent = Intent(this, MapDetailActivity::class.java)
            startActivity(intent)
        }

        addressRecyclerViewAdapter.setOnItemClickListener(object : AddressRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: ResultAddressList, Pos: Int) {
//                AddressManagerService(this).tryGetDetailAddress(Pos)
                revClick(Pos+1)
                showCustomToast("$Pos")
                index = Pos+1
            }
        })

        binding.icBack.setOnClickListener {
            finish()
        }
    }

    fun revClick(Pos:Int) {
        AddressManagerService(this).tryGetDetailAddress(Pos)
    }

    override fun onGetAddressSuccess(response: AddressListResponse) {
        if (response.result.isNotEmpty()) {
            addressList = response.result.toMutableList()
            addressRecyclerViewAdapter.addData(addressList)
            dismissLoadingDialog()
        }
    }

    override fun onGetAddressFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onGetDetailAddressSuccess(response: DetailAddressResponse) {
        // 받아온 정보를 가지고 detail로 이동
        val intent = Intent(this, MapDetailActivity::class.java)
        intent.putExtra("mainAddress",response.result.main_address)
        intent.putExtra("AddressGuide",response.result.address_guide)
        intent.putExtra("DetailAddress",response.result.detail_address)
        intent.putExtra("Status",response.result.status)
        intent.putExtra("AddressName",response.result.address_name)
        intent.putExtra("checkDelete", true)
        intent.putExtra("Index",index)
        startActivity(intent)
        Log.e(TAG, "onGetDetailAddressSuccess: ${response.result}", )

    }

    override fun onGetDetailAddressFailure(message: String) {
        TODO("Not yet implemented")
    }
}