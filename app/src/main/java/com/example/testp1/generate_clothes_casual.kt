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

class generate_clothes_casual : AppCompatActivity() {
    var back_casual: ImageButton? = null
    var casual_result: TextView? = null
    var casual_info: TextView? = null
    var casual_gen: Button? = null
    var isFormalMenuClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_clothes_casual)
        supportActionBar?.hide()
        init()
        back_casual!!.setOnClickListener {
            val intent = Intent(this, menu_clothes::class.java)
            startActivity(intent)
        }
        casual_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generatecasual()
        }
    }

    fun generatecasual() {
        // Clear the textview value to the default value
        casual_result?.text = "xxxxxxxx"
        casual_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            casual_result,
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
            "เสื้อโปโล",
            "เสื้อเชิ้ตไดนิมคลาสสิก",
            "เสื้อแขนยาว"

        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        casual_result?.text = selectedClothes

        when (selectedClothes) {
            "เสื้อโปโล" -> casual_info?.text =
                "สามารถคอมบินกับกางเกงยีนส์หรือช่วงขาของกางเกงแบบต่างๆ"

            "เสื้อเชิ้ตไดนิมคลาสสิก" -> casual_info?.text =
                "เหมาะสำหรับการสวมใส่ในทุกโอกาส ทั้งไปทำงานหรือกิจกรรมนอกบ้าน"

            "เสื้อแขนยาว" -> casual_info?.text =
                "เหมาะสำหรับกิจกรรมกีฬาหรือกิจกรรมนอกบ้าน"


            else -> casual_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_casual = findViewById(R.id.back_gen_casual)
        casual_result = findViewById(R.id.casual_result)
        casual_info = findViewById(R.id.casual_info)
        casual_gen = findViewById(R.id.casual_gen)
    }
}