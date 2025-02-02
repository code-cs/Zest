package com.example.zest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper= DatabaseHelper(this)

        var et_email = findViewById(R.id.et_email)as EditText
        var et_password = findViewById(R.id.et_password) as EditText
        var btn_cancel = findViewById(R.id.btn_cancel) as Button
        var btn_login = findViewById(R.id.btn_login) as Button

        //clears the email and password
        btn_cancel.setOnClickListener{
            et_email.setText("")
            et_password.setText("")
        }

        //submit button to login to the application
        btn_login.setOnClickListener{
            val email = et_email.text.toString().trim()
            val password=et_password.text.toString().trim()

            if(email.isEmpty()) {
                et_email.error="Email Required!"
                btn_login.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()) {
                    et_password.error="Password Required!"
                    btn_login.requestFocus()
                    return@setOnClickListener
            }

            if(dbHelper.userPresents(et_email.text.toString(), et_password.text.toString())) {
               // startActivity(Intent(this, HomeActivity::class.java))
                Toast.makeText(this,"You are logged in.", Toast.LENGTH_SHORT).show()

            }

            else
                Toast.makeText(this,"Incorrect Email or password.", Toast.LENGTH_SHORT).show()
        }

        //sign up
        signUp_login.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
