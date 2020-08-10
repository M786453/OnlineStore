package com.example.onlinestore

import android.app.AlertDialog
import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

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
            var temporaryPlaceOrderUrl = "http://192.168.10.2/OnlineStore/insert_temporary_order.php?email=${Person.email}" +
                    "&product_id=${Person.productId}&amount=${edtAmountOfProduct.text}"
            var requestQ:RequestQueue = Volley.newRequestQueue(fragmentView.context)
            var stringRequest = StringRequest(Request.Method.GET,temporaryPlaceOrderUrl, Response.Listener { response ->
                Toast.makeText(fragmentView.context,"Temporary order is placed.",Toast.LENGTH_SHORT).show()
            },Response.ErrorListener { error ->
                 var dialogue = AlertDialog.Builder(fragmentView.context)
                 dialogue.setTitle("Error")
                 dialogue.setMessage(error.message)
                 dialogue.create().show()
            })
            requestQ.add(stringRequest)
        }
        return fragmentView
    }

}
