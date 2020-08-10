package com.example.onlinestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_products.*

class Products : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        var tappedBrand = intent.getStringExtra("BRAND")
        var storeproductList = ArrayList<EProduct>()
        val productsUrl = "http://192.168.10.2/OnlineStore/fetch_products.php?brand="+
                            tappedBrand
        val requestQ = Volley.newRequestQueue(this@Products)
        val jsonAR = JsonArrayRequest(Request.Method.GET,productsUrl,null,
               Response.Listener { response ->

            for (index in 0.until(response.length())){
                storeproductList.add(EProduct(response.getJSONObject(index).getInt("id"), response.getJSONObject(index).getString("name"), response.getJSONObject(index).getInt("price"), response.getJSONObject(index).getString("picture")))
            }
                   product_activity_title_txt.text = "Products of $tappedBrand"
         var adapter = RvAdapter(this@Products,storeproductList)
                   recyclerView.layoutManager = LinearLayoutManager(this)
                   recyclerView.adapter = adapter


               },Response.ErrorListener { error ->
               Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()
            })
           requestQ.add(jsonAR)
    }
}
