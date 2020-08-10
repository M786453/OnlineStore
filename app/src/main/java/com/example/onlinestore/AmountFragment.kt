package com.example.onlinestore

import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton

class AmountFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var fragmentView = inflater.inflate(R.layout.fragment_amount, container, false)
        var edtAmountOfProduct = fragmentView.findViewById<EditText>(R.id.edtAmountOfProduct)
        var btnAddToCart = fragmentView.findViewById<ImageButton>(R.id.addToCartImgBtn)
        btnAddToCart.setOnClickListener {
            var intent = Intent(fragmentView.context,TemporaryPlaceOrder::class.java)
            startActivity(intent)
        }
        return fragmentView
    }

}
