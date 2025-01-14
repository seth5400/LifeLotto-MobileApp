package com.example.testp1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private val TAG: String = "Register Activity"
    private var regisEmail: EditText? = null
    private var regisPass: EditText? = null
    private var createAcc: Button? = null
    private var regisConfirmPass: EditText? = null
    private var regis_sign: TextView? = null
    private var regisUsername: EditText? = null
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val usersRef: DatabaseReference = database.getReference("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
        init()

        mAuth = FirebaseAuth.getInstance()

        if (mAuth!!.currentUser != null) {
            startActivity(Intent(this@Register, login1::class.java))
            finish()
        }

        createAcc?.setOnClickListener {
            val email = regisEmail?.text.toString().trim { it <= ' ' }
            val password = regisPass?.text.toString().trim { it <= ' ' }
            val confirmPassword = regisConfirmPass?.text.toString().trim { it <= ' ' }
            val username = regisUsername?.text.toString().trim { it <= ' ' }

            if (username.isEmpty()) {
                Toast.makeText(this, "Please enter your username.", Toast.LENGTH_LONG).show()
                Log.d(TAG, "Username was empty!")
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email address.", Toast.LENGTH_LONG).show()
                Log.d(TAG, "Email was empty!")
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(this, "Please enter your password.", Toast.LENGTH_LONG).show()
                Log.d(TAG, "Password was empty!")
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Password does not match.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    if (password.length < 6) {
                        Toast.makeText(this, "Password too short! Please enter minimum 6 characters.", Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Enter password less than 6 characters.")
                    } else {
                        Toast.makeText(this, "Authentication Failed: " + task.exception!!.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Authentication Failed: " + task.exception!!.message)
                    }
                } else {
                    // Registration successful, save username to Firebase Realtime Database
                    val userId = mAuth!!.currentUser?.uid
                    val user = HashMap<String, Any>()
                    user["username"] = username

                    if (userId != null) {
                        usersRef.child(userId).setValue(user)
                            .addOnSuccessListener {
                                Toast.makeText(this, "User data saved successfully", Toast.LENGTH_SHORT).show()
                                Log.d(TAG, "User data saved successfully")
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Failed to save user data: ${e.message}", Toast.LENGTH_SHORT).show()
                                Log.e(TAG, "Failed to save user data: ${e.message}")
                            }
                    }

                    Toast.makeText(this, "Create account successfully!", Toast.LENGTH_LONG).show()
                    Log.d(TAG, "Create account successfully!")
                    finish()
                }
            }
        }

        regis_sign!!.setOnClickListener {
            val intent = Intent(this, login1::class.java)
            startActivity(intent)
        }
    }
    private fun init() {
        regisUsername = findViewById(R.id.Register_user)
        regis_sign = findViewById(R.id.Register_sign)
        regisEmail = findViewById(R.id.Register_email)
        regisPass = findViewById(R.id.Register_password)
        regisConfirmPass = findViewById(R.id.Register_cpassword)
        createAcc = findViewById(R.id.Register_btn)
    }
}
