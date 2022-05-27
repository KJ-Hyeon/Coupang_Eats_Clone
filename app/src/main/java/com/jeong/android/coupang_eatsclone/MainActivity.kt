package com.jeong.android.coupang_eatsclone

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jeong.android.coupang_eatsclone.config.ApplicationClass.Companion.sSharedPreferences
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityMainBinding
import com.jeong.android.coupang_eatsclone.src.main.bookmark.BookMarkFragment
import com.jeong.android.coupang_eatsclone.src.main.home.HomeFragment
import com.jeong.android.coupang_eatsclone.src.main.join.JoinActivity
import com.jeong.android.coupang_eatsclone.src.main.login.LoginActivity
import com.jeong.android.coupang_eatsclone.src.main.order_list.OrderListFragment
import com.jeong.android.coupang_eatsclone.src.main.page.PageFragment
import com.jeong.android.coupang_eatsclone.src.main.search.SearchFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    var permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION)
    val permission_request = 99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if(!isPermitted()) {
            ActivityCompat.requestPermissions(this, permissions, permission_request)
        }

        supportFragmentManager.beginTransaction().replace(R.id.fl_main, HomeFragment()).commit()

        binding.bottomNav.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.fragment_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_main, HomeFragment())
                            .commit()
                    }
                    R.id.frgment_search -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_main, SearchFragment())
                            .commit()
                    }
                    R.id.fragment_bookmark -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_main, BookMarkFragment())
                            .commit()
                    }
                    R.id.fragment_order_list -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_main, OrderListFragment())
                            .commit()
                    }
                    R.id.fragment_page -> {
                        val login_state = sSharedPreferences.getString("X-ACCESS-TOKEN",null)
//                        if(!login_state.isNullOrEmpty()) {
                        // 이 버튼을 클릭할때 마다 자동로그인 여부 SP를 검사해줘야 하나?
                        if (!login_state.isNullOrEmpty()) {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.fl_main, PageFragment())
                                .commit()
                        }
                        else {
                            val bottomsheet = layoutInflater.inflate(R.layout.page_bottom_sheet, null)
                            val bottomSheetDlg = BottomSheetDialog(this@MainActivity)
                            bottomSheetDlg.setContentView(bottomsheet)
                            bottomSheetDlg.show()
                            val login_email = bottomSheetDlg.findViewById<Button>(R.id.btn_login_email)
                            val join = bottomSheetDlg.findViewById<TextView>(R.id.tv_bottom_sheet_join)
                            join?.setOnClickListener {
                                val intent = Intent(this@MainActivity, JoinActivity::class.java)
                                startActivity(intent)
                                bottomSheetDlg.dismiss()
                            }
                            login_email?.setOnClickListener {
                                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                                startActivity(intent)
                                bottomSheetDlg.dismiss()
                            }
                        }

                    }
                }
                true
            }
            selectedItemId = R.id.fragment_home
        }
    }
    fun isPermitted(): Boolean {
        for (perm in permissions) {
            // perm의 대한 권한을 가지고 있으면 PERMISSION_GRANTED를 리턴하고 가지고 있지 않다면 PERMISSION_DENIED를 리턴
            if(ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }
}