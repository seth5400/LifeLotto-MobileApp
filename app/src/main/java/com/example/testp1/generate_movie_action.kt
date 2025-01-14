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

class generate_movie_action : AppCompatActivity() {
    var back_movieaction: ImageButton? = null
    var action_result: TextView? = null
    var action_info: TextView? = null
    var action_gen: Button? = null
    var isFormalMenuClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_movie_action)
        supportActionBar?.hide()
        init()
        back_movieaction!!.setOnClickListener {
            val intent = Intent(this, menu_movie::class.java)
            startActivity(intent)
        }
        action_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generateaction()
        }
    }

    fun generateaction() {
        // Clear the textview value to the default value
        action_result?.text = "xxxxxxxx"
        action_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            action_result,
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
            "แบทแมน: อัศวินรัตติกาล",
            "ไอรอนแมน",
            "กับตันอเมริกา"

        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        action_result?.text = selectedClothes

        when (selectedClothes) {
            "แบทแมน: อัศวินรัตติกาล" -> action_info?.text =
                "ซุปเปอร์ฮีโร่แอคชั่นซุปเปอร์ฮีโร่เรื่องราวของแบทแมนต่อสู้กับศัตรูร้ายอย่างจ็อกเกอร์ในเมืองโกทแธม"

            "ไอรอนแมน" -> action_info?.text =
                "เรื่องราวของทอนี่ สตาร์ก ในบทไอรอนแมนที่ต่อสู้กับภัยคุกคามทั้งจากภัยธรรมชาติและศัตรูที่เจ๋ง"

            "กับตันอเมริกา" -> action_info?.text =
                "สตีฟ โรเจอร์ส กับตัน อเมริกาที่ต่อสู้กับอารยธรรมผิวขาวสยองของไฮดรา."


            else -> action_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_movieaction = findViewById(R.id.back_gen_action)
        action_result = findViewById(R.id.action_result)
        action_info = findViewById(R.id.action_info)
        action_gen = findViewById(R.id.action_gen)
    }
}