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

class generate_clothes_casualchic : AppCompatActivity() {
    var back_chic: ImageButton? = null
    var chic_result: TextView? = null
    var chic_info: TextView? = null
    var chic_gen: Button? = null
    var isFormalMenuClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_clothes_casualchic)
        supportActionBar?.hide()
        init()
        back_chic!!.setOnClickListener {
            val intent = Intent(this, menu_clothes::class.java)
            startActivity(intent)
        }
        chic_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generatechic()
        }
    }

    fun generatechic() {
        // Clear the textview value to the default value
        chic_result?.text = "xxxxxxxx"
        chic_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            chic_result,
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
            "เสื้อครอปท็อปผ้าลูกไม้",
            "เสื้อคอสูงและลูกไม้หลอด",
            "เสื้อเชิ้ตโอเวอร์ไซส์"

        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        chic_result?.text = selectedClothes

        when (selectedClothes) {
            "เสื้อครอปท็อปผ้าลูกไม้" -> chic_info?.text =
                "เสื้อครอปท็อปที่มีลายลูกไม้เล็ก ๆ ทำให้ดูเป็นทางการและสวยงาม"

            "เสื้อคอสูงและลูกไม้หลอด" -> chic_info?.text =
                "สามารถแต่งกับกางเกงขาสั้นหรือกระโปรงสวย ๆ สำหรับลุคที่เข้ากับทรงเสื้อ"

            "เสื้อเชิ้ตโอเวอร์ไซส์" -> chic_info?.text =
                "เสื้อเชิ้ตที่มีทรงโอเวอร์ไซส์ทำให้ดูคูลและสะดุดตา"


            else -> chic_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_chic = findViewById(R.id.back_gen_chic)
        chic_result = findViewById(R.id.chic_result)
        chic_info = findViewById(R.id.chic_info)
        chic_gen = findViewById(R.id.chic_gen)
    }
}