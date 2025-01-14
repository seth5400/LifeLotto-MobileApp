package com.example.testp1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class login1 : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    private val TAG: String = "Login Activity"

    var Login_btn: Button? = null
    var Login_user : EditText? = null
    var Login_password : EditText? = null
    var Login_Signup: TextView? = null

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login1)
        supportActionBar?.hide()
        init()

        mAuth = FirebaseAuth.getInstance()

        if (mAuth!!.currentUser != null) {
            startActivity(Intent(this@login1,
                MainActivity::class.java))
            finish()
        }
        Login_btn?.setOnClickListener {
            val email = Login_user?.text.toString().trim { it <= ' ' }
            val password = Login_password?.text.toString().trim { it <= ' ' }

            if (email.isEmpty()) {
                Toast.makeText(this,"Please enter your email address.",Toast.LENGTH_LONG).show()
                Log.d(TAG, "Email was empty!")
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(this,"Please enter your password.",Toast.LENGTH_LONG).show()
                Log.d(TAG, "Password was empty!")
                return@setOnClickListener
            }

            mAuth!!.signInWithEmailAndPassword(email,
                password).addOnCompleteListener { task ->


                if (!task.isSuccessful) {

                    if (password.length < 6) {

                        Login_password?.error = "Please check your password.Password must have minimum 6 characters."
                        Log.d(TAG, "Enter password less than 6 characters.")
                    } else {
                        Toast.makeText(this,"Authentication Failed: " +
                                task.exception!!.message,Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Authentication Failed: " +
                                task.exception!!.message)
                    }
                } else {
                    Toast.makeText(this,"Sign in successfully!",Toast.LENGTH_LONG).show()
                    Log.d(TAG, "Sign in successfully!")
                    startActivity(Intent(this@login1,
                        MainActivity::class.java))
                    finish()
                }
            }
        }

        Login_Signup!!.setOnClickListener {
            val intent = Intent(this,Register::class.java)
            startActivity(intent)

        }

    }
    fun init(){
        Login_btn = findViewById(R.id.Login_btn)
        Login_user = findViewById(R.id.Login_user)
        Login_password = findViewById(R.id.Login_password)
        Login_Signup = findViewById(R.id.Login_signup)
    }
}