package com.jeong.android.coupang_eatsclone.src.main.page

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.jeong.android.coupang_eatsclone.MainActivity
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.config.ApplicationClass.Companion.sSharedPreferences
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivitySettingBinding
import com.jeong.android.coupang_eatsclone.src.main.page.models.PostLogoutResponse

class SettingActivity : BaseActivity<ActivitySettingBinding>(ActivitySettingBinding::inflate) ,
            SettingInterface{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvLogout.setOnClickListener {
            val mDialog = LayoutInflater.from(this).inflate(R.layout.dialog_logout,null)
            val builder = AlertDialog.Builder(this)
                .setView(mDialog)

            val logoutDialog = builder.show()
            val deleteBtn = logoutDialog.findViewById<TextView>(R.id.btn_delete)
            val cancleBtn = logoutDialog.findViewById<TextView>(R.id.btn_cancle)

            cancleBtn.setOnClickListener {
                logoutDialog.dismiss()
            }
            deleteBtn.setOnClickListener {
                SettingService(this).tryPostLogout()
                logoutDialog.dismiss()
            }
        }

    }

    override fun onPostLogoutSuccess(response: PostLogoutResponse) {
        finish()
        // SP에 저장했던 주소와 토큰 삭제?
        val edit = sSharedPreferences.edit()
        edit.putString("X-ACCESS-TOKEN", null)
        edit.putString("mainAddress", null)
        edit.putInt("AddressId",-1)
        edit.commit()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onPostLogoutFailure(message: String) {
        TODO("Not yet implemented")
    }
}