package com.example.onlinestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
     btnSignUp.setOnClickListener {
         if (edtSignUpPassword.text.toString().equals(edtSignUpConfirmPassword.text.toString())){
             //User Registration
             val serverUrl = "http://192.168.10.4/OnlineStore/register_new_users.php?email=" +
                     edtSignUpEmail.text.toString() + "&username="+edtSignUpUsername.text.toString()+
                     "&password="+edtSignUpPassword.text.toString()
             val requestQ: RequestQueue = Volley.newRequestQueue(this@SignUp)
             val stringRequest = StringRequest(Request.Method.GET,serverUrl,Response.Listener { response ->

                        showDialog(response)
             },Response.ErrorListener { error ->
                    showDialog(error.message.toString())
             })
             requestQ.add(stringRequest)
         }else{
             showDialog("Password does not match.")
         }

     }

    }
   private fun showDialog(message:String){
        val dialog = AlertDialog.Builder(this@SignUp)
        dialog.setTitle("Message")
        dialog.setMessage(message)
        dialog.create().show()
    }
    }

