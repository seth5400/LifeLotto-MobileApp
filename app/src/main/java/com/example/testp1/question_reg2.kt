package com.example.testp1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class question_reg2 : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val usersRef: DatabaseReference = database.getReference("users")
    private var btnMale: ConstraintLayout? = null
    private var btnFemale: ConstraintLayout? = null
    private var finishGender: Button? = null
    private var selectedGender: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_reg2)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        init()

        btnMale?.setOnClickListener {
            selectedGender = "Male"
            Toast.makeText(this, "Selected gender: Male", Toast.LENGTH_SHORT).show()
        }

        btnFemale?.setOnClickListener {
            selectedGender = "Female"
            Toast.makeText(this, "Selected gender: Female", Toast.LENGTH_SHORT).show()
        }

        finishGender?.setOnClickListener {
            val userId = mAuth?.currentUser?.uid
            if (userId != null && selectedGender != null) {
                val userGenderRef = usersRef.child(userId).child("gender")
                userGenderRef.setValue(selectedGender)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Gender data saved successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@question_reg2, MainActivity::class.java))
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Failed to save gender: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun init() {
        btnMale = findViewById(R.id.btn_male)
        btnFemale = findViewById(R.id.btn_female)
        finishGender = findViewById(R.id.finish_gender)
    }
}