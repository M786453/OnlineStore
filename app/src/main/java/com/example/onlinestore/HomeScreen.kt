package com.example.onlinestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        val fetchBrandUrl = "http://192.168.10.2/OnlineStore/fetch_brands.php"
        var brandList = ArrayList<String>()
        val requestQ: RequestQueue = Volley.newRequestQueue(this@HomeScreen)
        val jAR = JsonArrayRequest(Request.Method.GET,fetchBrandUrl,null,Response.Listener { response ->
            for (jObjectIndex in 0.until(response.length())){
                brandList.add(response.getJSONObject(jObjectIndex).getString("brand"))
            }
            var adapter = ArrayAdapter(this@HomeScreen,R.layout.brand_listview_row,brandList)
            brandListView.adapter = adapter
        },Response.ErrorListener { error ->
            Toast.makeText(this@HomeScreen,error.message,Toast.LENGTH_SHORT).show()
        })
        requestQ.add(jAR)
        brandListView.setOnItemClickListener { parent, view, position, id ->
            var tappedBrand = brandList.get(position)
            var intent = Intent(this@HomeScreen,Products::class.java)
            intent.putExtra("BRAND",tappedBrand)
            startActivity(intent)
        }
    }
}
