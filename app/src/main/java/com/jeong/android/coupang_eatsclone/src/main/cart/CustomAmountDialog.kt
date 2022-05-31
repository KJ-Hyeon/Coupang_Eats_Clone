package com.jeong.android.coupang_eatsclone.src.main.cart

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.jeong.android.coupang_eatsclone.R

class CustomAmountDialog(val context: Context){
    private val dialog = Dialog(context)
    private lateinit var listener : TextClickListener
    private var lastNum = 0

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

        when (lastNum) {
            1 -> {
                tvOne.setBackgroundColor(context.getColor(R.color.bg_amount_dialog))
                tvOne.setTextColor(context.getColor(R.color.btn_blue))
            }
            2 -> {
                tvTwo.setBackgroundColor(context.getColor(R.color.bg_amount_dialog))
                tvTwo.setTextColor(context.getColor(R.color.btn_blue))
            }
            3 -> {
                tvThree.setBackgroundColor(context.getColor(R.color.bg_amount_dialog))
                tvThree.setTextColor(context.getColor(R.color.btn_blue))
            }
            4 -> {
                tvFour.setBackgroundColor(context.getColor(R.color.bg_amount_dialog))
                tvFour.setTextColor(context.getColor(R.color.btn_blue))
            }
            5 -> {
                tvFive.setBackgroundColor(context.getColor(R.color.bg_amount_dialog))
                tvFive.setTextColor(context.getColor(R.color.btn_blue))
            }
            6 -> {
                tvSix.setBackgroundColor(context.getColor(R.color.bg_amount_dialog))
                tvSix.setTextColor(context.getColor(R.color.btn_blue))
            }
            7 -> {
                tvSeven.setBackgroundColor(context.getColor(R.color.bg_amount_dialog))
                tvSeven.setTextColor(context.getColor(R.color.btn_blue))
            }
            8 -> {
                tvEight.setBackgroundColor(context.getColor(R.color.bg_amount_dialog))
                tvEight.setTextColor(context.getColor(R.color.btn_blue))
            }
            9 -> {
                tvNine.setBackgroundColor(context.getColor(R.color.bg_amount_dialog))
                tvNine.setTextColor(context.getColor(R.color.btn_blue))
            }
            10 -> {
                tvTen.setBackgroundColor(context.getColor(R.color.bg_amount_dialog))
                tvTen.setTextColor(context.getColor(R.color.btn_blue))
            }
        }


        tvOne.setOnClickListener {
            listener.onClick("1")
            lastNum = 1
            dialog.dismiss()
        }
        tvTwo.setOnClickListener {
            listener.onClick("2")
            lastNum = 2
            dialog.dismiss()
        }
        tvThree.setOnClickListener {
            listener.onClick("3")
            lastNum = 3
            dialog.dismiss()
        }
        tvFour.setOnClickListener {
            listener.onClick("4")
            lastNum = 4
            dialog.dismiss()
        }
        tvFive.setOnClickListener {
            listener.onClick("5")
            lastNum = 5
            dialog.dismiss()
        }
        tvSix.setOnClickListener {
            listener.onClick("6")
            lastNum = 6
            dialog.dismiss()
        }
        tvSeven.setOnClickListener {
            listener.onClick("7")
            lastNum = 7
            dialog.dismiss()
        }
        tvEight.setOnClickListener {
            listener.onClick("8")
            lastNum = 8
            dialog.dismiss()
        }
        tvNine.setOnClickListener {
            listener.onClick("9")
            lastNum = 9
            dialog.dismiss()
        }
        tvTen.setOnClickListener {
            listener.onClick("10")
            lastNum = 10
            dialog.dismiss()
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