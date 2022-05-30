package com.jeong.android.coupang_eatsclone.src.main.cart

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.jeong.android.coupang_eatsclone.R

class CustomAmountDialog(val context: Context) {
    private val dialog = Dialog(context)
    private lateinit var listener : TextClickListener

    fun showDialog() {
        dialog.setContentView(R.layout.dialog_amount)

        // 좀 더 깔끔 하게 코드를 짤수 없나?
        val tvOne = dialog.findViewById<TextView>(R.id.tv_one)
        val tvTwo = dialog.findViewById<TextView>(R.id.tv_two)
        val tvThree = dialog.findViewById<TextView>(R.id.tv_three)
        val tvFour = dialog.findViewById<TextView>(R.id.tv_four)
        val tvFive = dialog.findViewById<TextView>(R.id.tv_five)
        val tvSix = dialog.findViewById<TextView>(R.id.tv_six)
        val tvSeven = dialog.findViewById<TextView>(R.id.tv_seven)
        val tvEight = dialog.findViewById<TextView>(R.id.tv_eight)
        val tvNine = dialog.findViewById<TextView>(R.id.tv_nine)
        val tvTen = dialog.findViewById<TextView>(R.id.tv_ten)
        //tvOne.setBackgroundColor(context.getColor(R.color.bg_amount_dialog))
        //tvOne.setTextColor(context.getColor(R.color.btn_blue))
        tvOne.setOnClickListener {
            listener.onClick("1")
        }

        dialog.show()
    }
    interface TextClickListener {
        fun onClick(text : String)
    }
    fun setOnClickListener(listener: TextClickListener) {
        this.listener = listener
    }


}