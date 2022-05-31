package com.jeong.android.coupang_eatsclone.src.main.order

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityOrderBinding
import com.jeong.android.coupang_eatsclone.src.main.order.models.MenuOption
import com.jeong.android.coupang_eatsclone.src.main.order.models.OrderResponse
import com.jeong.android.coupang_eatsclone.src.main.order.models.PostAddCartRequest
import com.jeong.android.coupang_eatsclone.src.main.order.models.PostAddCartResponse
import kotlin.math.abs

class OrderActivity : BaseActivity<ActivityOrderBinding>(ActivityOrderBinding::inflate),
            OrderInterface{

    private lateinit var orderRecyclerViewAdapter: OrderRecyclerViewAdapter
    private var orderList = mutableListOf<MenuOption>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val intent = intent
        val menuIndex = intent.getIntExtra("menuIndex", -1)
        val storeIndex = intent.getIntExtra("storeIndex", -1)
        var checkRadio = -1

        OrderService(this).tryGetMenu(storeIndex, menuIndex)

//        this.window?.apply {
//            this.statusBarColor = Color.TRANSPARENT
//            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        }

        appbarView()

        orderRecyclerViewAdapter = OrderRecyclerViewAdapter(orderList)
        binding.revOptionMenu.adapter = orderRecyclerViewAdapter

        binding.btnPlus.setOnClickListener {
            val amount = binding.tvOrderMenuAmount.text.toString().toInt() + 1
            binding.tvOrderMenuAmount.text = amount.toString()
        }
        binding.btnMinus.setOnClickListener {
            val amount = binding.tvOrderMenuAmount.text.toString().toInt() - 1
            if (amount < 0) {
                binding.tvOrderMenuAmount.text = "0"
            } else {
                binding.tvOrderMenuAmount.text = amount.toString()
            }
        }
        orderRecyclerViewAdapter.setOnItemClickListener(object : OrderRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(v: View, Pos: Int) {
                checkRadio = Pos
            }
        })
        binding.btnAddCart.setOnClickListener {
            val menuAmount = binding.tvOrderMenuAmount.text.toString()
            val postAddCartRequest = PostAddCartRequest(menuAmount.toInt(),checkRadio)

            OrderService(this).tryPostAddCart(postAddCartRequest,storeIndex, menuIndex)
        }


    }

    private fun appbarView() {
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener{ appBarLayout, verticalOffset ->
            if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                //접혔을때
                binding.tvAppBarMenuName.visibility = View.VISIBLE
                binding.icBack.setImageResource(R.drawable.ic_arrow_back_black)
                binding.icShare.setImageResource(R.drawable.ic_share_black)

            } else {
                //펴졌을때
                binding.tvAppBarMenuName.visibility = View.GONE
                binding.icBack.setImageResource(R.drawable.ic_arrow_back_white)
                binding.icShare.setImageResource(R.drawable.ic_share_white)
                this.window?.apply {
                    this.statusBarColor = Color.TRANSPARENT
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                }
            }
        })
    }

    override fun onGetDetailMenuSuccess(response: OrderResponse) {
        Glide.with(binding.root)
            .load(response.result.menu_img_url)
            .into(binding.ivOrderMenu)
        binding.tvOrderMenuName.text = response.result.menu_name
        binding.tvAppBarMenuName.text = response.result.menu_name
        binding.tvOrderMenuPrice.text = "${response.result.menu_price}원"

        orderRecyclerViewAdapter.addData(response.result.menu_option)
    }

    override fun onGetDetailMenuFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onPostAddCartSuccess(response: PostAddCartResponse) {
        showCustomToast("카트에 추가되었습니다.")
    }

    override fun onPostAddCartFailure(message: String) {
        TODO("Not yet implemented")
    }
    fun getStatusHeight(context: Context): Int {
        var result = 0
        val resourceId: Int = context.resources.getIdentifier("status_bar_height","dimen","android")
        if (resourceId > 0) {
            result = context.resources.getDimension(resourceId).toInt()
        }
        return result
    }
}