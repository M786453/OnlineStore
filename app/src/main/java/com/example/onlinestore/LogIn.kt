package com.example.onlinestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
            val serverUrl = "http://192.168.10.2/OnlineStore/login_users.php?email="+
                             edtLogInEmail.text.toString() + "&password="+edtLogInPassword.text.toString()
            val requestQ:RequestQueue = Volley.newRequestQueue(this@LogIn)
            val stringRequest = StringRequest(Request.Method.GET,serverUrl, Response.Listener { response ->
                if (response.toString().equals("User does not exists.")){
                    showDialog(response)
                }else{
                    Person.email = edtLogInEmail.text.toString()
                    Toast.makeText(this@LogIn,response,Toast.LENGTH_SHORT).show()
                    transitionToAnotherActivity(HomeScreen::class.java)
                }


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
    private fun transitionToAnotherActivity(anotherActivity:java.lang.Class<*>){
        val transitionIntent= Intent(this@LogIn,anotherActivity)
        startActivity(transitionIntent)
    }
}
