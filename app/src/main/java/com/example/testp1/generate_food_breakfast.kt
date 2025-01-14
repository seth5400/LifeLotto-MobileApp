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

class generate_food_breakfast : AppCompatActivity() {
    var back_breakfast: ImageButton? = null
    var breakfast_result: TextView? = null
    var breakfast_info: TextView? = null
    var breakfast_gen: Button? = null
    var isFormalMenuClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_food_breakfast)
        supportActionBar?.hide()
        init()
        back_breakfast!!.setOnClickListener {
            val intent = Intent(this, menu_food::class.java)
            startActivity(intent)
        }
        breakfast_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generatebreakfast()
        }
    }

    fun generatebreakfast() {
        // Clear the textview value to the default value
        breakfast_result?.text = "xxxxxxxx"
        breakfast_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            breakfast_result,
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
            "ข้าวต้มเห็ด",
            "แซนด์วิชอะโวคาโด",
            "ออมเล็ตไส้กรอก",
            "แซนด์วิชแฮมชีส",
            "สลัดราดซอสครีม"

        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        breakfast_result?.text = selectedClothes

        when (selectedClothes) {
            "ข้าวต้มเห็ด" -> breakfast_info?.text =
                "ข้าวต้มเห็ดเป็นอาหารเช้ายอดนิยม เห็ดมีโปรตีนสูงและใยอาหาร ทานคู่กับผักสด"

            "แซนด์วิชอะโวคาโด" -> breakfast_info?.text =
                "แซนด์วิชอะโวคาโด เป็นอาหารเช้าที่มีไขมันดี ทานคู่กับขนมปังโฮลเกรน ผักสด และไข่ต้ม"

            "ออมเล็ตไส้กรอก" -> breakfast_info?.text =
                "ออมเล็ตไส้กรอกเป็นอาหารเช้ายอดนิยม  ไข่มีโปรตีนสูง ไส้กรอกมีไขมันและโปรตีน"

            "แซนด์วิชแฮมชีส" -> breakfast_info?.text =
                "แซนด์วิชแฮมชีสเป็นอาหารเช้ายอดนิยม  แฮมมีโปรตีน ชีสมีไขมันและแคลเซียม"

            "สลัดราดซอสครีม" -> breakfast_info?.text =
                "สลัดราดซอสครีมเป็นอาหารเช้าที่มีประโยชน์ เหมาะแก่การบำรุงร่างกาย"

            else -> breakfast_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_breakfast = findViewById(R.id.back_gen_breakfast)
        breakfast_result = findViewById(R.id.breakfast_result)
        breakfast_info = findViewById(R.id.breakfast_info)
        breakfast_gen = findViewById(R.id.gen_breakfast)
    }
}