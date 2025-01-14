package com.example.testp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class menu_music : AppCompatActivity() {
    var back_music: ImageButton? = null
    var music_thai: Button? = null
    var music_inter: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_music)
        supportActionBar?.hide()
        init()

        back_music!!.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }
        music_thai!!.setOnClickListener {
            val intent = Intent(this,menu_music_thai::class.java)
            startActivity(intent)
        }
        music_inter!!.setOnClickListener {
            val intent = Intent(this,menu_music_nation::class.java)
            startActivity(intent)
        }
    }
    fun init(){
        back_music = findViewById(R.id.back_music)
        music_thai = findViewById(R.id.btn_thai)
        music_inter = findViewById(R.id.btn_inter)

    }
}