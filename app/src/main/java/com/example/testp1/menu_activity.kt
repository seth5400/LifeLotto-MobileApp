package com.example.testp1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class menu_activity : AppCompatActivity() {
    var back_activity: ImageButton? = null
    var activity_physical: Button? = null
    var activity_self: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_activity)
        supportActionBar?.hide()
        init()
        back_activity!!.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }
        activity_physical!!.setOnClickListener {
            val intent = Intent(this,generate_actvty::class.java)
            startActivity(intent)

        }
        activity_self!!.setOnClickListener {
            val intent = Intent(this,generate_activit_selfdevelopment::class.java)
            startActivity(intent)

        }
    }
    fun init(){
        back_activity = findViewById(R.id.back_activity)
        activity_physical = findViewById(R.id.Activity_physical)
        activity_self = findViewById(R.id.activtiy_self)
    }
}