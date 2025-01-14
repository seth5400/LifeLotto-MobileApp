package com.example.testp1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class menu_clothes : AppCompatActivity() {
    var Clothes_formal: Button? = null
    var Clothes_casual: Button? = null
    var Clothes_casualchic: Button? = null
    var back_clothes: ImageButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_clothes)
        supportActionBar?.hide()
        init()

        Clothes_formal!!.setOnClickListener {
            val intent = Intent(this, generate_clothes_formal::class.java)
            startActivity(intent)
        }
        Clothes_casual!!.setOnClickListener {
            val intent = Intent(this, generate_clothes_casual::class.java)
            startActivity(intent)
        }
        Clothes_casualchic!!.setOnClickListener {
            val intent = Intent(this, generate_clothes_casualchic::class.java)
            startActivity(intent)
        }
        back_clothes!!.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
    fun init(){
        Clothes_formal = findViewById(R.id.clothes_formal)
        Clothes_casual = findViewById(R.id.clothes_casual)
        Clothes_casualchic = findViewById(R.id.clothes_casualchic)
        back_clothes = findViewById(R.id.back_clothes)

    }
}