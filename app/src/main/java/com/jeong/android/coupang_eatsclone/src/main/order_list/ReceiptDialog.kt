package com.jeong.android.coupang_eatsclone.src.main.order_list

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.jeong.android.coupang_eatsclone.databinding.DialogReceiptBinding

class ReceiptDialog: DialogFragment() {

    private lateinit var binding: DialogReceiptBinding


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
        binding = DialogReceiptBinding.inflate(layoutInflater)

    }
}