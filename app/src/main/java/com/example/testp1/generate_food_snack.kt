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

class generate_food_snack : AppCompatActivity() {
    var back_snack: ImageButton? = null
    var snack_result: TextView? = null
    var snack_info: TextView? = null
    var snack_gen: Button? = null
    var isFormalMenuClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_food_snack)
        supportActionBar?.hide()
        init()
        back_snack!!.setOnClickListener {
            val intent = Intent(this, menu_food::class.java)
            startActivity(intent)
        }
        snack_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generatesnack()
        }
    }

    fun generatesnack() {
        // Clear the textview value to the default value
        snack_result?.text = "xxxxxxxx"
        snack_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            snack_result,
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
            "ช็อกโกแลตมูส",
            "ไอศกรีม",
            "โยเกิร์ต"

        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        snack_result?.text = selectedClothes

        when (selectedClothes) {
            "ช็อกโกแลตมูส" -> snack_info?.text =
                "ของหวานชนิดหนึ่งที่ทำจากช็อกโกแลตและวิปครีม มีรสชาติหวานกลมกล่อมและเนื้อสัมผัสเนียนนุ่ม"

            "ไอศกรีม" -> snack_info?.text =
                "ของหวานชนิดหนึ่งที่ทำจากน้ำแข็งผสมน้ำตาลและนม มีรสชาติหวานโปร่งใสและมักเสิร์ฟเย็น มีรูปแบบหลากหลายรสชาติ"

            "โยเกิร์ต" -> snack_info?.text =
                "อาหารที่ทำจากนมเชื้อสดหรือเชื้อหมัก มักมีรสชาติเปรี้ยวอ่อนๆ และมักเสิร์ฟเย็น มีส่วนผสมสำคัญคือเอนไซม์เย็นโรงหนังที่ทำให้นมกลายเป็นโยเกิร์ต"

            else -> snack_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_snack = findViewById(R.id.back_gen_snack)
        snack_result = findViewById(R.id.snack_result)
        snack_info = findViewById(R.id.snack_info)
        snack_gen = findViewById(R.id.gen_snack)
    }
}