package com.example.testp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class generate_music : AppCompatActivity() {
    var back_musicthai: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_music_thai)
        supportActionBar?.hide()
        init()
        back_musicthai!!.setOnClickListener {
            val intent = Intent(this,menu_music_thai::class.java)
            startActivity(intent)
        }

    }
    fun init(){
        back_musicthai = findViewById(R.id.back_gen_thai)
    }
}