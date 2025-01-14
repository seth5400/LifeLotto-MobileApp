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

class generate_food_lunch : AppCompatActivity() {
    var back_lunch: ImageButton? = null
    var lunch_result: TextView? = null
    var lunch_info: TextView? = null
    var lunch_gen: Button? = null
    var isFormalMenuClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_food_lunch)
        supportActionBar?.hide()
        init()
        back_lunch!!.setOnClickListener {
            val intent = Intent(this, menu_food::class.java)
            startActivity(intent)
        }
        lunch_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generatelunch()
        }
    }

    fun generatelunch() {
        // Clear the textview value to the default value
        lunch_result?.text = "xxxxxxxx"
        lunch_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            lunch_result,
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
            "ลาบเห็ด",
            "ส้มตำเต้าหู้",
            "แกงพะแนงหมู",
            "สเต็กเนื้อ",
            "พาสต้าผัดซอสมะเขือเทศ"

        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        lunch_result?.text = selectedClothes

        when (selectedClothes) {
            "ลาบเห็ด" -> lunch_info?.text =
                "เป็นอาหารมื้อกลางวันแบบไทยๆ ที่มีรสชาติเปรี้ยวเผ็ด"

            "ส้มตำเต้าหู้" -> lunch_info?.text =
                "เป็นอาหารมื้อกลางวันแบบไทยๆ ที่มีรสชาติเปรี้ยวเผ็ด "

            "แกงพะแนงหมู" -> lunch_info?.text =
                "เป็นอาหารมื้อกลางวันแบบไทยๆ ที่มีรสชาติเข้มข้น"

            "สเต็กเนื้อ" -> lunch_info?.text =
                "เป็นอาหารมื้อกลางวันแบบตะวันตกที่มีรสชาติอร่อย ไม"

            "พาสต้าผัดซอสมะเขือเทศ" -> lunch_info?.text =
                "เป็นเมนูอาหารยอดนิยมที่ทำง่าย อร่อย และเหมาะสำหรับทุกโอกาส"

            else -> lunch_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_lunch = findViewById(R.id.back_gen_lunch)
        lunch_result = findViewById(R.id.lunch_result)
        lunch_info = findViewById(R.id.lunch_info)
        lunch_gen = findViewById(R.id.gen_lunch)
    }
}