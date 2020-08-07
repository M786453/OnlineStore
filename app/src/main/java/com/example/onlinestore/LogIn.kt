package com.example.onlinestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_log_in.*

class LogIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        btnLogIn.setOnClickListener {
            val serverUrl = "http://192.168.10.4/OnlineStore/login_users.php?email="+
                             edtLogInEmail.text.toString() + "&password="+edtLogInPassword.text.toString()
            val requestQ:RequestQueue = Volley.newRequestQueue(this@LogIn)
            val stringRequest = StringRequest(Request.Method.GET,serverUrl, Response.Listener { response ->
                  showDialog(response)
            },Response.ErrorListener { error ->
                showDialog(error.message.toString())
            })
            requestQ.add(stringRequest)
        }
    }
    private fun showDialog(message:String){
        val dialog = AlertDialog.Builder(this@LogIn)
        dialog.setTitle("Message")
        dialog.setMessage(message)
        dialog.create().show()
    }
}
