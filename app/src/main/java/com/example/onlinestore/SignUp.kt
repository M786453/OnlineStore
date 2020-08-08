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
                         if (response.toString().equals("A user with this email already exists!")){
                             showDialog(response)
                         }else{
                             Toast.makeText(this@SignUp,response, Toast.LENGTH_SHORT).show()
                             transitionToAnotherActivity(HomeScreen::class.java)

                         }

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
    private fun transitionToAnotherActivity(anotherActivity:java.lang.Class<*>){
        val transitionIntent= Intent(this@SignUp,anotherActivity)
        startActivity(transitionIntent)
    }
    }

