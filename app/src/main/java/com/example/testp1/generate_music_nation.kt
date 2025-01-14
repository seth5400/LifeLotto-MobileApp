package com.example.testp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class generate_music_nation : AppCompatActivity() {
    var back_musicinter: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_music_nation)
        supportActionBar?.hide()
        init()
        back_musicinter!!.setOnClickListener {
            val intent = Intent(this,menu_music_nation::class.java)
            startActivity(intent)
        }
    }
    fun init(){
        back_musicinter = findViewById(R.id.back_gen_inter)
    }
}

