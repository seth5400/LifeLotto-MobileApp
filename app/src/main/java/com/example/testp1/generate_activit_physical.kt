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

class generate_activit_physical : AppCompatActivity() {
    var back_physical: ImageButton? = null
    var physical_result: TextView? = null
    var physical_info: TextView? = null
    var physical_gen: Button? = null
    var isFormalMenuClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_activit_physical)
        supportActionBar?.hide()
        init()
        back_physical!!.setOnClickListener {
            val intent = Intent(this, menu_activity::class.java)
            startActivity(intent)
        }
        physical_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generatephysical()
        }
    }

    fun generatephysical() {
        // Clear the textview value to the default value
        physical_result?.text = "xxxxxxxx"
        physical_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            physical_result,
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
            "บาสเกตบอล",
            "โยคะ",
            "เทนนิส",
            "เดิน",
            "ฮูลาฮูป"

        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        physical_result?.text = selectedClothes

        when (selectedClothes) {
            "บาสเกตบอล" -> physical_info?.text =
                "กีฬาทีมที่เล่นบนพื้นสนามสี่เหลี่ยมโดยใช้ลูกบาสเกตบอล มีการโยงและทำประตูเพื่อทำคะแนน"

            "โยคะ" -> physical_info?.text =
                "การฝึกบริหารร่างกายและจิตใจผ่านการเคลื่อนไหว มุ่งเน้นการหายใจและยืดเหยียดเพื่อเสริมสร้างความยืดหยุ่น"

            "เทนนิส" -> physical_info?.text =
                "กีฬาที่เล่นบนสนามยาวโดยใช้แร๊กเกตสำหรับการตีลูก ต้องใช้การวิ่งและการรับมือ"

            "เดิน" -> physical_info?.text =
                "กิจกรรมฝึกออกกำลังกายที่ง่ายและเหมาะสำหรับทุกเพศและวัย"

            "ฮูลาฮูป" -> physical_info?.text =
                "การหมุนวงล้อที่วางอยู่ที่อกศรีษะ ใช้เทคนิคการเคลื่อนไหวและการควบคุมตัวเอง"


            else -> physical_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_physical = findViewById(R.id.back_gen_physical)
        physical_result = findViewById(R.id.physical_result)
        physical_info = findViewById(R.id.physical_info)
        physical_gen = findViewById(R.id.gen_physical)
    }
}