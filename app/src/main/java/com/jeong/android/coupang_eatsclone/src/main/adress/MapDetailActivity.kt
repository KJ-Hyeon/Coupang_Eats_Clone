package com.jeong.android.coupang_eatsclone.src.main.adress

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
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityMapDetailBinding
import com.jeong.android.coupang_eatsclone.src.main.join.JoinActivity
import com.jeong.android.coupang_eatsclone.src.main.login.LoginActivity

class MapDetailActivity :
    BaseActivity<ActivityMapDetailBinding>(ActivityMapDetailBinding::inflate) {

    var menu_list = ArrayList<ImageView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        binding.tvAddress.text = intent.getStringExtra("Address")
        binding.tvRoadAddress.text = intent.getStringExtra("RoadAddress")

        menu_list = arrayListOf<ImageView>(binding.detailMenuHome, binding.detailMenuOffice, binding.detailMenuEtc)

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
                btn_ignore?.setOnClickListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    bottomSheetDlg.dismiss()
                    finish()
                }
            }
        }

        binding.detailMenuHome.setOnClickListener {
            if (binding.detailMenuHome.tag == 1){
                binding.detailMenuHome.tag = 2
            }else {
                binding.detailMenuHome.tag = 1
            }
            binding.detailMenuOffice.tag = 3
            binding.detailMenuEtc.tag = 5
            change_menu_image()
        }

        binding.detailMenuOffice.setOnClickListener {
            if (binding.detailMenuOffice.tag == 3){
                binding.detailMenuOffice.tag = 4
            }else {
                binding.detailMenuOffice.tag = 3
            }
            binding.detailMenuHome.tag = 1
            binding.detailMenuEtc.tag = 5
            change_menu_image()
        }

        binding.detailMenuEtc.setOnClickListener {
            if (binding.detailMenuEtc.tag == 5){
                binding.detailMenuEtc.tag = 6
                binding.etAdressNickname.visibility = View.VISIBLE
            }else {
                binding.detailMenuEtc.tag = 5
                binding.etAdressNickname.visibility = View.GONE
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
}