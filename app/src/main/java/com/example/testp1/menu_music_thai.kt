package com.example.testp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class menu_music_thai : AppCompatActivity() {
    var back_thaim: ImageButton? = null
    var thaijazzbtn: Button? = null
    var thaipopbtn: Button? = null
    var thairockbtn: Button? = null
    var thairapbtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_music_thai)
        supportActionBar?.hide()
        init()
        thaijazzbtn!!.setOnClickListener {
            val intent = Intent(this,generate_music_thai_jazz::class.java)
            startActivity(intent)
        }
        thaipopbtn!!.setOnClickListener {
            val intent = Intent(this,generate_music_thai_pop::class.java)
            startActivity(intent)
        }
        thairockbtn!!.setOnClickListener {
            val intent = Intent(this,generate_music_thai_rock::class.java)
            startActivity(intent)
        }
        thairapbtn!!.setOnClickListener {
            val intent = Intent(this,generate_music_thai_rap::class.java)
            startActivity(intent)
        }
        back_thaim!!.setOnClickListener {
            val intent = Intent(this,menu_music::class.java)
            startActivity(intent)
        }

    }
    fun init(){
        thaijazzbtn = findViewById(R.id.btn_Jazz_thai)
        thaipopbtn = findViewById(R.id.btn_pop_thai)
        thairockbtn = findViewById(R.id.btn_rock_thai)
        thairapbtn = findViewById(R.id.btn_rap_thai)
        back_thaim = findViewById(R.id.back_music_thaio)

    }
}