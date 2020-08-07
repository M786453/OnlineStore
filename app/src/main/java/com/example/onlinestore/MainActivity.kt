package com.example.onlinestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnMainSignUp.setOnClickListener {
            transitionToAnotherActivity(SignUp::class.java)
        }
        btnMainLogIn.setOnClickListener {
            transitionToAnotherActivity(LogIn::class.java)
        }
    }
    private fun transitionToAnotherActivity(anotherActivity:java.lang.Class<*>){
        val transitionIntent=Intent(this@MainActivity,anotherActivity)
        startActivity(transitionIntent)
    }
}
