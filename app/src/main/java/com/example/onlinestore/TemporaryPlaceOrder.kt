package com.example.onlinestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_temporary_place_order.*

class TemporaryPlaceOrder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temporary_place_order)
        var orderList = ArrayList<String>()
        var fetchOrdersUrl = "http://192.168.10.2/OnlineStore/fetch_temporary_order.php?email=${Person.email}"
        var requestQ:RequestQueue = Volley.newRequestQueue(this@TemporaryPlaceOrder)
        var jsonAR = JsonArrayRequest(Request.Method.GET,fetchOrdersUrl,null,Response.Listener { response ->
            for (jObjectIndex in 0.until(response.length())){
                orderList.add("ID: ${response.getJSONObject(jObjectIndex).get("id")} \n Name: ${response.getJSONObject(jObjectIndex).get("name")} \n " +
                        "Amount: ${response.getJSONObject(jObjectIndex).get("amount")} \n" + "Price: ${response.getJSONObject(jObjectIndex).get("price")} \n Brand: ${response.getJSONObject(jObjectIndex).get("brand")} \n " +
                        "Email: ${response.getJSONObject(jObjectIndex).get("email")}")
            }
            var adapter = ArrayAdapter(this@TemporaryPlaceOrder,android.R.layout.simple_list_item_1,orderList)
            orderListView.adapter = adapter
        },Response.ErrorListener {error ->
            var dialogue = AlertDialog.Builder(this@TemporaryPlaceOrder)
            dialogue.setTitle("Error")
            dialogue.setMessage(error.message)
            dialogue.create().show()
        })
        requestQ.add(jsonAR)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.temporary_order_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.continueShoppingItem){
            transitionToAnotherActivity()
            finish()
        }else if(item.itemId==R.id.cancelOrderItem){
            var cancelOrderUrl = "http://192.168.10.2/OnlineStore/cancel_order.php?email=${Person.email}"
            var requestQ:RequestQueue = Volley.newRequestQueue(this@TemporaryPlaceOrder)
            var stringRequest = StringRequest(Request.Method.GET,cancelOrderUrl,Response.Listener { response ->
                transitionToAnotherActivity()
                finish()
            },Response.ErrorListener { error ->
                var dialogue = AlertDialog.Builder(this@TemporaryPlaceOrder)
                dialogue.setTitle("Error")
                dialogue.setMessage(error.message)
                dialogue.create().show()
            })
            requestQ.add(stringRequest)
        }else if(item.itemId==R.id.verifyOrderItem){

        }
        return super.onOptionsItemSelected(item)
    }
    fun transitionToAnotherActivity(){
        var intent = Intent(this@TemporaryPlaceOrder,HomeScreen::class.java)
        startActivity(intent)
    }
}