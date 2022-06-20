package com.jeong.android.coupang_eatsclone.src.main.cart

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jeong.android.coupang_eatsclone.R
import com.jeong.android.coupang_eatsclone.config.ApplicationClass
import com.jeong.android.coupang_eatsclone.config.ApplicationClass.Companion.decimal
import com.jeong.android.coupang_eatsclone.config.ApplicationClass.Companion.sSharedPreferences
import com.jeong.android.coupang_eatsclone.config.BaseFragment
import com.jeong.android.coupang_eatsclone.databinding.FragmentCartDeliveryBinding
import com.jeong.android.coupang_eatsclone.src.main.cart.models.*

class CartDeliveryFrgment :BaseFragment<FragmentCartDeliveryBinding>(FragmentCartDeliveryBinding::bind, R.layout.fragment_cart_delivery),
            CartInterface{

    private lateinit var cartRecyclerViewAdapter: CartRecyclerViewAdapter
    private var cartMenuList = mutableListOf<CartMenu>()
    private var storeId = -1
    private lateinit var cartIdList : MutableList<Int>
    private var deliveryRequest : Int = 1
    private var storeRequest : String = " "

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CartService(this).tryGetCart()

        val mainAddress = sSharedPreferences.getString("mainAddress",null)
        val roadAddress = sSharedPreferences.getString("RoadAddress",null)
        binding?.tvCartMainAddress?.text = mainAddress
        if (roadAddress.isNullOrEmpty()) {
            binding?.tvCartRoadAddress?.text = "화성시 병점3로 132-6"
        } else {
            binding?.tvCartRoadAddress?.text = roadAddress
        }

        cartRecyclerViewAdapter = CartRecyclerViewAdapter(cartMenuList,requireContext())
        binding?.revCartMenu?.adapter = cartRecyclerViewAdapter

        cartRecyclerViewAdapter.setOnItemClickListener(object : CartRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: CartMenu, amount: Int) {
                revClick(data, amount)
            }
            override fun onDeletClick(data: CartMenu) {
                val mDialog = LayoutInflater.from(context).inflate(R.layout.dialog_delete,null)
                val builder = AlertDialog.Builder(context)
                    .setView(mDialog)

                val customDeleteDialog = builder.show()
                val deleteBtn = customDeleteDialog.findViewById<TextView>(R.id.btn_delete)
                val cancleBtn = customDeleteDialog.findViewById<TextView>(R.id.btn_cancle)

                cancleBtn.setOnClickListener {
                    customDeleteDialog.dismiss()
                }
                deleteBtn.setOnClickListener {
                    revDeleteClick(data)
                    customDeleteDialog.dismiss()
                }
            }
        })

        binding?.btnOrder?.setOnClickListener {
            // 주문하기 버튼
            val addressId = sSharedPreferences.getInt("AddressId",-1)
            storeRequest = binding?.tvMessageBoss?.text.toString()
            val postCartRequest = PostCartRequest(addressId, storeId, 1, deliveryRequest, storeRequest)
            CartService(this).tryPostCart(postCartRequest, cartIdList )
        }

        binding?.tvMessageDelivery?.setOnClickListener {
            // 배달 요청사항 클릭
            val requestBottomSheet = layoutInflater.inflate(R.layout.request_bottom_sheet, null)
            val requestBottomSheetDlg = BottomSheetDialog(requireContext())
            requestBottomSheetDlg.setContentView(requestBottomSheet)
            requestBottomSheetDlg.show()
            // 간소화 할 수 있는 방법...
            val contentRequest1 = requestBottomSheetDlg.findViewById<TextView>(R.id.content1)
            val contentRequest2 = requestBottomSheetDlg.findViewById<TextView>(R.id.content2)
            val contentRequest3 = requestBottomSheetDlg.findViewById<TextView>(R.id.content3)
            val contentRequest4 = requestBottomSheetDlg.findViewById<TextView>(R.id.content4)
            val contentRequest5 = requestBottomSheetDlg.findViewById<TextView>(R.id.content5)
            val contentRequest6 = requestBottomSheetDlg.findViewById<TextView>(R.id.content6)
            val contentRequest7 = requestBottomSheetDlg.findViewById<TextView>(R.id.content7)
            val contentRequest8 = requestBottomSheetDlg.findViewById<TextView>(R.id.content8)

            contentRequest1?.setOnClickListener {
                deliveryRequest = 1
                binding?.tvMessageDelivery?.text = contentRequest1.text
                requestBottomSheetDlg.dismiss()
            }
            contentRequest2?.setOnClickListener {
                deliveryRequest = 2
                binding?.tvMessageDelivery?.text = contentRequest2.text
                requestBottomSheetDlg.dismiss()
            }
            contentRequest3?.setOnClickListener {
                deliveryRequest = 3
                binding?.tvMessageDelivery?.text = contentRequest3.text
                requestBottomSheetDlg.dismiss()
            }
            contentRequest4?.setOnClickListener {
                deliveryRequest = 4
                binding?.tvMessageDelivery?.text = contentRequest4.text
                requestBottomSheetDlg.dismiss()
            }
            contentRequest5?.setOnClickListener {
                deliveryRequest = 5
                binding?.tvMessageDelivery?.text = contentRequest5.text
                requestBottomSheetDlg.dismiss()
            }
            contentRequest6?.setOnClickListener {
                deliveryRequest = 6
                binding?.tvMessageDelivery?.text = contentRequest6.text
                requestBottomSheetDlg.dismiss()
            }
            contentRequest7?.setOnClickListener {
                deliveryRequest = 7
                binding?.tvMessageDelivery?.text = contentRequest7.text
                requestBottomSheetDlg.dismiss()
            }
            contentRequest8?.setOnClickListener {
                deliveryRequest = 8
                binding?.tvMessageDelivery?.text = contentRequest8.text
                requestBottomSheetDlg.dismiss()
            }


        }
    }

    fun revClick(data: CartMenu, amount: Int) {
        val patchCartRequest = PatchCartRequest(amount)
        CartService(this).tryPatchCart(patchCartRequest, storeId, data.cart_id )
    }

    fun revDeleteClick(data: CartMenu) {
        val deleteCartRequest = DeleteCartRequest(data.cart_id)
        CartService(this).trydeleteCart(deleteCartRequest)
    }


    override fun onGetCartSuccess(response: CartResponse) {
        binding?.tvCartStoreName?.text = response.result.store_name
        var menuPrice = 0
        for (i in response.result.cartMenu) {
            menuPrice += i.price
        }
        binding?.btnOrder?.text = "배달주문 ${decimal.format(menuPrice)}원 결제하기"
        if (response.result.is_cheetah_delivery == "N") {
            binding?.cartCheetah?.visibility = View.INVISIBLE
        } else {
            binding?.cartCheetah?.visibility = View.VISIBLE
        }
//        binding?.btnOrder?.text = response.result.
        cartRecyclerViewAdapter.clearData()
        cartRecyclerViewAdapter.addData(response.result.cartMenu)
        storeId = response.result.store_id
        for (i in response.result.cartMenu) {
            cartIdList.add(i.cart_id)
        }
    }

    override fun onGetCartNull() {
        cartRecyclerViewAdapter.clearData()
        val intent = Intent(requireContext(), CartEmptyActivity::class.java)
        requireContext().startActivity(intent)
//        activity?.supportFragmentManager
//            ?.beginTransaction()
//            ?.remove(this)
//            ?.commit()
        activity?.finish()
    }

    override fun onGetCartFailure(message: String) {
        showCustomToast("$message")
    }

    override fun onPatchCartSuccess(response: PatchCartResponse) {
        cartRecyclerViewAdapter.clearData()
        CartService(this).tryGetCart()
    }

    override fun onPatchCartFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onDeleteCartSuccess(response: DeleteCartResponse) {
        CartService(this).tryGetCart()
    }

    override fun onDeleteCartFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onPostCartSuccess(response: PostCartResponse) {
        // 주문하기 버튼 클릭시 실행
    }

    override fun onPostCartFailure(message: String) {
        TODO("Not yet implemented")
    }
}