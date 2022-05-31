package com.jeong.android.coupang_eatsclone.src.main.cart

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.TextView
import com.jeong.android.coupang_eatsclone.R

class CustomDeleteDialog(val context: Context) {
    private val dialog = Dialog(context)
    private lateinit var listener: ClickListener

    fun showDialog() {
        dialog.setContentView(R.layout.dialog_delete)
        dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        val deleteBtn = dialog.findViewById<TextView>(R.id.btn_delete)
        val cancleBtn = dialog.findViewById<TextView>(R.id.btn_cancle)
        deleteBtn.setOnClickListener {

        }
        dialog.show()
    }
    interface ClickListener {
        fun onDeleteClick()
    }
    fun setOnClickListener(listener: ClickListener) {
        this.listener = listener
    }
}