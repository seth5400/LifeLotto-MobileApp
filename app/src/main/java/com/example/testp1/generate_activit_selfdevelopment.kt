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

class generate_activit_selfdevelopment : AppCompatActivity() {
    var back_self: ImageButton? = null
    var self_result: TextView? = null
    var self_info: TextView? = null
    var self_gen: Button? = null
    var isFormalMenuClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_activit_selfdevelopment)
        supportActionBar?.hide()
        init()
        back_self!!.setOnClickListener {
            val intent = Intent(this, menu_activity::class.java)
            startActivity(intent)
        }
        self_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generateself()
        }
    }

    fun generateself() {
        // Clear the textview value to the default value
        self_result?.text = "xxxxxxxx"
        self_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            self_result,
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
            "นั่งสมาธิ",
            "จดบันทึก",
            "อ่านหนังสือ",
            "ทำอาหาร",
            "ฟังพอดแคสต์"

        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        self_result?.text = selectedClothes

        when (selectedClothes) {
            "นั่งสมาธิ" -> self_info?.text =
                "ช่่วยให้จิตใจสงบ ผ่อนคลาย ลดความเครียด"

            "จดบันทึก" -> self_info?.text =
                "ช่วยให้จดจำสิ่งต่างๆ ได้ดีขึ้น เรียบเรียงความคิด และ พัฒนาทักษะการคิดวิเคราะห์"

            "อ่านหนังสือ" -> self_info?.text =
                "ช่วยให้ได้รับความรู้และมุมมองใหม่ๆ และ พัฒนาทักษะการคิดวิเคราะห์"

            "ทำอาหาร" -> self_info?.text =
                "ช่วยให้เรียนรู้ทักษะใหม่ ฝึกความอดทน และ สร้างผลงานที่น่าภูมิใจ"

            "ฟังพอดแคสต์" -> self_info?.text =
                "ช่วยให้เรียนรู้และได้รับความรู้สะดวก เพราะ สามารถฟังได้ทุกที่ ทุกเวลา"


            else -> self_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_self = findViewById(R.id.back_gen_self)
        self_result = findViewById(R.id.self_result)
        self_info = findViewById(R.id.self_info)
        self_gen = findViewById(R.id.gen_self)
    }
}