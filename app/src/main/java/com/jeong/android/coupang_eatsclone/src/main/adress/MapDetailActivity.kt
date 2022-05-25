package com.jeong.android.coupang_eatsclone.src.main.adress

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jeong.android.coupang_eatsclone.MainActivity
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.ApplicationClass.Companion.sSharedPreferences
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityMapDetailBinding
import com.jeong.android.coupang_eatsclone.src.main.adress.models.AddressResponse
import com.jeong.android.coupang_eatsclone.src.main.adress.models.PostAddressRequest
import java.lang.Math.round

class MapDetailActivity :
    BaseActivity<ActivityMapDetailBinding>(ActivityMapDetailBinding::inflate), MapDetailInterface {

    var menu_list = ArrayList<ImageView>()
    var addressName = " "
    var status = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val mainAddress = intent.getStringExtra("Address")
        val userLatitude = round(intent.getDoubleExtra("UserLatitude",0.0) * 100) / 100 .toDouble()
        val userLongitude = round(intent.getDoubleExtra("UserLongitude",0.0) * 100) / 100 .toDouble()
        val checkDelete = intent.getBooleanExtra("checkDelete", false)

        val userId = sSharedPreferences.getInt("USER_ID", -1)

        binding.tvAddress.text = intent.getStringExtra("Address")
        binding.tvRoadAddress.text = intent.getStringExtra("RoadAddress")

        menu_list = arrayListOf<ImageView>(binding.detailMenuHome, binding.detailMenuOffice, binding.detailMenuEtc)

        if (checkDelete) {
            binding.btnDelete.visibility = View.VISIBLE
        }else {
            binding.btnDelete.visibility = View.GONE
        }

        binding.btnDelete.setOnClickListener {

        }

        binding.btnComplete.setOnClickListener {
            if (binding.etDetailAdress.text.toString().trim().isEmpty()) {
                Log.e("TAG", "onCreate:if문 진입",)
                val bottomsheet = layoutInflater.inflate(R.layout.map_detail_bottom_sheet, null)
                val bottomSheetDlg = BottomSheetDialog(this@MapDetailActivity)
                bottomSheetDlg.setContentView(bottomsheet)
                bottomSheetDlg.show()
                val btn_input_detail = bottomSheetDlg.findViewById<Button>(R.id.btn_input_detail)
                val btn_ignore = bottomSheetDlg.findViewById<TextView>(R.id.btn_ignore)
                btn_input_detail?.setOnClickListener {
                    binding.etDetailAdress.requestFocus()
                    bottomSheetDlg.dismiss()
                }
//                btn_ignore?.setOnClickListener {
//                    val detailAddress = binding.etDetailAdress.text.toString()
//                    val addressGuide = binding.etGuideRoad.text.toString()
//                    if (binding.detailMenuEtc.tag == 6) {
//                        addressName = if (binding.etAdressNickname.text.toString().isEmpty()) {
//                            binding.tvAddress.text.toString()
//                        } else {
//                            binding.etAdressNickname.text.toString()
//                        }
//                    }
//                    val postRequest = PostAddressRequest(mainAddress!!,null,addressGuide,
//                        userId!!,userLongitude!!, userLatitude!!,addressName, status)
//                    MapDetailService(this).tryPostAddress(postRequest)
//                    bottomSheetDlg.dismiss()
//                    finish()
//                }
            } else {
                val detailAddress = binding.etDetailAdress.text.toString()
                val addressGuide = binding.etGuideRoad.text.toString()
                if (binding.detailMenuEtc.tag == 6) {
                    addressName = if (binding.etAdressNickname.text.toString().isEmpty()) {
                        binding.tvAddress.text.toString()
                    } else {
                        binding.etAdressNickname.text.toString()
                    }
                }
                val postRequest = PostAddressRequest(mainAddress!!,detailAddress,addressGuide,
                    userId!!,userLongitude, userLatitude,addressName, status)
                MapDetailService(this).tryPostAddress(postRequest)
                showCustomToast("$userLatitude,$userLongitude")
            }
        }

        binding.detailMenuHome.setOnClickListener {
            if (binding.detailMenuHome.tag == 1){
                binding.detailMenuHome.tag = 2
                binding.etAdressNickname.visibility = View.GONE
                addressName = "home"
                status = "H"
            }else {
                binding.detailMenuHome.tag = 1
                addressName = " "
                status = " "
            }
            binding.detailMenuOffice.tag = 3
            binding.detailMenuEtc.tag = 5
            change_menu_image()
        }

        binding.detailMenuOffice.setOnClickListener {
            if (binding.detailMenuOffice.tag == 3){
                binding.detailMenuOffice.tag = 4
                binding.etAdressNickname.visibility = View.GONE
                addressName = "office"
                status = "C"
            }else {
                binding.detailMenuOffice.tag = 3
                addressName = " "
                status = " "
            }
            binding.detailMenuHome.tag = 1
            binding.detailMenuEtc.tag = 5
            change_menu_image()
        }

        binding.detailMenuEtc.setOnClickListener {
            if (binding.detailMenuEtc.tag == 5){
                binding.detailMenuEtc.tag = 6
                binding.etAdressNickname.visibility = View.VISIBLE
                status = "C"
            }else {
                binding.detailMenuEtc.tag = 5
                binding.etAdressNickname.visibility = View.GONE
                addressName = " "
                status = " "
            }
            binding.detailMenuHome.tag = 1
            binding.detailMenuOffice.tag = 3
            change_menu_image()
        }

    }
    private fun change_menu_image() {
        for (image in menu_list) {
            if (image.tag == 1 ) {
                image.setImageResource(R.drawable.map_detail_home_non_selected)
                image.setBackgroundResource(R.drawable.bg_detail_menu_non_selected)
            } else if (image.tag == 2) {
                image.setImageResource(R.drawable.map_detail_home_selected)
                image.setBackgroundResource(R.drawable.bg_detail_menu_selected)
            } else if (image.tag ==3) {
                image.setImageResource(R.drawable.map_detail_office_non_selected)
                image.setBackgroundResource(R.drawable.bg_detail_menu_non_selected)
            } else if (image.tag ==4) {
                image.setImageResource(R.drawable.map_detail_office_selected)
                image.setBackgroundResource(R.drawable.bg_detail_menu_selected)
            } else if (image.tag ==5) {
                image.setImageResource(R.drawable.map_detail_etc_non_selected)
                image.setBackgroundResource(R.drawable.bg_detail_menu_non_selected)
            } else {
                image.setImageResource(R.drawable.map_detail_etc_selected)
                image.setBackgroundResource(R.drawable.bg_detail_menu_selected)
            }
        }
    }

    override fun onPostAddressSuccess(response: AddressResponse) {
        showCustomToast("${response.code}")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onPostAdressFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}