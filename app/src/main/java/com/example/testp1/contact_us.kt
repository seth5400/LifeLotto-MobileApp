package com.example.testp1

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.testp1.ui.notifications.NotificationsFragment

class contact_us : AppCompatActivity() {
    private var contact_back: ImageButton? = null
    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextMessage: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)
        supportActionBar?.hide()

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextMessage = findViewById(R.id.editTextMessage)
        contact_back = findViewById(R.id.contact_back_setting)

        contact_back?.setOnClickListener {
            val intent = Intent(this, NotificationsFragment::class.java)
            startActivity(intent)

        }


    }

}