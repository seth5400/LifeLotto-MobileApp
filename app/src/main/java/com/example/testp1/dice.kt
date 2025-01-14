package com.example.testp1

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class dice : AppCompatActivity() {
    var dice_back: ImageButton? = null
    private lateinit var roll_text :TextView
    private  lateinit var resultImages :ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)
        supportActionBar?.hide()

        roll_text = findViewById(R.id.roll_text)
        resultImages = findViewById(R.id.dice_img)
        dice_back = findViewById(R.id.dice_back)

        resultImages.setOnClickListener{
            val animation :Animation = AnimationUtils.loadAnimation(applicationContext,R.anim.animation)
            animation.start()

            rollDice()
        }

        dice_back?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun rollDice(){
        val randomInt = (1..6).random()
        val  drawbleImage = when(randomInt){
            1-> R.drawable.dice_11
            2-> R.drawable.dice_22
            3-> R.drawable.dice_33
            4-> R.drawable.dice_44
            5-> R.drawable.dice_55
            else -> R.drawable.dice_66
        }
        val tvText = randomInt.toString()

        resultImages.setImageResource(drawbleImage)
        roll_text.text = "Dice Roll os $tvText"

        Toast.makeText(this,"Dice Roll os $tvText",Toast.LENGTH_SHORT).show()
    }

}