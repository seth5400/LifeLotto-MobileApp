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

class generate_movie_romantic : AppCompatActivity() {
    var back_movieromantic: ImageButton? = null
    var romantic_result: TextView? = null
    var romantic_info: TextView? = null
    var romantic_gen: Button? = null
    var isFormalMenuClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_movie_romantic)
        supportActionBar?.hide()
        init()
        back_movieromantic!!.setOnClickListener {
            val intent = Intent(this, menu_movie::class.java)
            startActivity(intent)
        }
        romantic_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generateromantic()
        }
    }

    fun generateromantic() {
        // Clear the textview value to the default value
        romantic_result?.text = "xxxxxxxx"
        romantic_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            romantic_result,
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
            "A Whisker Away",
            "Your name",
            "Weathering with your"

        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        romantic_result?.text = selectedClothes

        when (selectedClothes) {
            "A Whisker Away" -> romantic_info?.text =
                "เป็นภาพยนตร์อนิเมชั่นญี่ปุ่นเรื่องราวของเด็กหญิงที่สามารถแปลงร่างเป็นแมวเพื่อหลีกเลี่ยงปัญหาและพบกับความจริงในตัวเอง"

            "Your name" -> romantic_info?.text =
                "เป็นภาพยนตร์อนิเมชั่นญี่ปุ่นที่เล่าเรื่องราวของคู่รักวัยรุ่นที่สามารถสลับร่างกันเมื่อตื่นขึ้นในเช้าวันหนึ่ง"

            "Weathering with your" -> romantic_info?.text =
                "เป็นเรื่องราวของคู่รักวัยรุ่นที่พบกันในกรุงโตเกียวที่ฝนตกอย่างต่อเนื่องและพบกับสาวที่มีความสามารถพิเศษในการควบคุมสภาพอากาศ"


            else -> romantic_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_movieromantic = findViewById(R.id.back_gen_romantic)
        romantic_result = findViewById(R.id.romantic_result)
        romantic_info = findViewById(R.id.romantic_info)
        romantic_gen = findViewById(R.id.romantic_gen)
    }
}