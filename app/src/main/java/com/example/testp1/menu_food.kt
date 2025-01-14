package com.example.testp1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class menu_food : AppCompatActivity() {
    var back_food: ImageButton? = null
    var food_breakfast: Button? = null
    var food_lunch: Button? = null
    var food_dinner: Button? = null
    var food_snack: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_food)
        supportActionBar?.hide()
        init()
        back_food!!.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        food_breakfast!!.setOnClickListener {
            val intent = Intent(this, generate_food_breakfast::class.java)
           startActivity(intent)
     }
        food_lunch!!.setOnClickListener {
            val intent = Intent(this, generate_food_lunch::class.java)
            startActivity(intent)
        }
        food_dinner!!.setOnClickListener {
            val intent = Intent(this, generate_food_dinner::class.java)
            startActivity(intent)
        }
        food_snack!!.setOnClickListener {
            val intent = Intent(this, generate_food_snack::class.java)
            startActivity(intent)
        }
    }
    fun init(){
        back_food = findViewById(R.id.back_food)
        food_breakfast = findViewById(R.id.btn_breakfast)
        food_lunch = findViewById(R.id.btn_lunch)
        food_dinner = findViewById(R.id.btn_dinner)
        food_snack = findViewById(R.id.btn_Snack)

    }
}