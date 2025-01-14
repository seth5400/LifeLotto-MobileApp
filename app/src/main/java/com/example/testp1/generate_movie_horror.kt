package com.example.testp1

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import kotlin.random.Random

class generate_movie_horror : AppCompatActivity() {
    var back_moviehorror: ImageButton? = null
    var horror_result: TextView? = null
    var horror_info: TextView? = null
    var horror_gen: Button? = null
    var isFormalMenuClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_movie_horror)
        supportActionBar?.hide()
        init()
        back_moviehorror!!.setOnClickListener {
            val intent = Intent(this, menu_movie::class.java)
            startActivity(intent)
        }
        horror_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generatehorror()
        }
    }

    fun generatehorror() {
        // Clear the textview value to the default value
        horror_result?.text = "xxxxxxxx"
        horror_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            horror_result,
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
            "The Conjuring",
            "Annabelle",
            "The Nun"

        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        horror_result?.text = selectedClothes

        when (selectedClothes) {
            "The Conjuring" -> horror_info?.text =
                "เรื่องราวของนักบวชชื่อเอ็ด และลอร์เรน วอร์เรน ที่ได้รับคำขอให้ช่วยชีวิตครอบครัววอร์เรนจากการกีดขวางของตำนานของผีดุที่อาศัยอยู่ในบ้านเก่า"

            "Annabelle" -> horror_info?.text =
                "เรื่องราวของตุ๊กตา Annabelle ที่ก่อให้เกิดความร้ายและความสยองขวัญต่อครอบครัวที่เป็นเจ้าของ"

            "The Nun" -> horror_info?.text =
                "เรื่องราวของนักบวชถูกส่งไปสืบสวนเหตุการณ์ลึกลับที่เกิดขึ้นในหอของคณะนักบวช"


            else -> horror_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_moviehorror = findViewById(R.id.back_gen_horror)
        horror_result = findViewById(R.id.horror_result)
        horror_info = findViewById(R.id.horror_info)
        horror_gen = findViewById(R.id.horror_gen)
    }
}