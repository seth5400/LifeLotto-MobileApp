package com.example.testp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class menu_music_nation : AppCompatActivity() {
    var back_nation: ImageButton? = null
    var interjazzbtn: Button? = null
    var interpopbtn: Button? = null
    var interrockbtn: Button? = null
    var interrapbtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_music_nation)
        supportActionBar?.hide()
        init()
        back_nation!!.setOnClickListener {
            val intent = Intent(this,menu_music::class.java)
            startActivity(intent)
        }
        interjazzbtn!!.setOnClickListener {
            val intent = Intent(this,generate_music_inter_jazz::class.java)
            startActivity(intent)
        }
        interpopbtn!!.setOnClickListener {
            val intent = Intent(this,generate_music_inter_pop::class.java)
            startActivity(intent)
        }
        interrockbtn!!.setOnClickListener {
            val intent = Intent(this,generate_music_inter_rock::class.java)
            startActivity(intent)
        }
        interrapbtn!!.setOnClickListener {
            val intent = Intent(this,generate_music_inter_rap::class.java)
            startActivity(intent)
        }
    }
    fun init(){
        back_nation = findViewById(R.id.back_music_inter)
        interjazzbtn = findViewById(R.id.btn_jazz_inter)
        interpopbtn = findViewById(R.id.btn_pop_inter)
        interrockbtn = findViewById(R.id.btn_rock_inter)
        interrapbtn = findViewById(R.id.btn_rap_inter)
    }
}