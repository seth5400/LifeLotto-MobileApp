package com.example.testp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class menu_movie : AppCompatActivity() {
    var back_movie: ImageButton? = null
    var movie_action: Button? = null
    var movie_horror: Button? = null
    var movie_romantic: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_movie)
        supportActionBar?.hide()
        init()

        back_movie!!.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }
        movie_action!!.setOnClickListener {
            val intent = Intent(this,generate_movie_action::class.java)
            startActivity(intent)
        }
        movie_horror!!.setOnClickListener {
            val intent = Intent(this,generate_movie_horror::class.java)
            startActivity(intent)
        }
        movie_romantic!!.setOnClickListener {
            val intent = Intent(this,generate_movie_romantic::class.java)
            startActivity(intent)
        }
    }

    fun init(){
        back_movie = findViewById(R.id.back_movie)
        movie_action = findViewById(R.id.btn_action)
        movie_horror = findViewById(R.id.btn_horror)
        movie_romantic = findViewById(R.id.btn_romantic)

    }
}