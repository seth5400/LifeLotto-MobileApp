package com.example.testp1

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class generate_food_dinner : AppCompatActivity() {
    var back_dinner: ImageButton? = null
    var dinner_result: TextView? = null
    var dinner_info: TextView? = null
    var dinner_gen: Button? = null
    var isFormalMenuClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_food_dinner)
        supportActionBar?.hide()
        init()
        back_dinner!!.setOnClickListener {
            val intent = Intent(this, menu_food::class.java)
            startActivity(intent)
        }
        dinner_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generatedinner()
        }
    }

    fun generatedinner() {
        // Clear the textview value to the default value
        dinner_result?.text = "xxxxxxxx"
        dinner_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            dinner_result,
            View.ROTATION_X,
            0f,
            7200f
        ) // Rotate twice (7200 degrees)
        rotationXAnimator.duration = 4000 // 4 seconds
        rotationXAnimator.interpolator =
            AccelerateDecelerateInterpolator() // Add acceleration and deceleration

        // Set an AnimatorListenerAdapter to handle onAnimationEnd
        rotationXAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                // When the animation ends, call showRandomText() to display the result
                showRandomText()
            }
        })

        // Start the animation
        rotationXAnimator.start()
    }

    fun showRandomText() {
        val allClothes = listOf(
            "สมูทตี้ผลไม้และผักโขม",
            "ขนมปังปิ้งอะโวคาโด",
            "ผัดกะเพราไก่",
            "ต้มยำกุ้ง",
            "ข้าวผัดผักสด"

        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        dinner_result?.text = selectedClothes

        when (selectedClothes) {
            "สมูทตี้ผลไม้และผักโขม" -> dinner_info?.text =
                "เป็นอาหารมื้อดึกที่ดีต่อสุขภาพและอุดมไปด้วยวิตามิน แร่ธาตุ และใยอาหาร"

            "ขนมปังปิ้งอะโวคาโด" -> dinner_info?.text =
                "เป็นตัวเลือกมื้อดึกที่ง่ายและอร่อย "

            "ผัดกะเพราไก่" -> dinner_info?.text =
                "เป็นอาหารมื้อเย็นแบบไทยๆ ที่มีรสชาติเผ็ดร้อน "

            "ต้มยำกุ้ง" -> dinner_info?.text =
                "เป็นอาหารมื้อเย็นแบบไทยๆ ที่มีรสชาติเปรี้ยวเผ็ด"

            "ข้าวผัดผักสด" -> dinner_info?.text =
                "ข้าวผัดที่ใช้ผักสดหลากหลายชนิดมาผัดรวมกัน"


            else -> dinner_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_dinner = findViewById(R.id.back_gen_dinner)
        dinner_result = findViewById(R.id.dinner_result)
        dinner_info = findViewById(R.id.dinner_info)
        dinner_gen = findViewById(R.id.gen_dinner)
    }
}