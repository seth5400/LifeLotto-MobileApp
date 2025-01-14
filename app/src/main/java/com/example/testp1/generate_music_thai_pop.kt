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

class generate_music_thai_pop : AppCompatActivity() {
    var back_musicpopthai: ImageButton? = null
    var popthai_result: TextView? = null
    var popthai_info: TextView? = null
    var popthai_gen: Button? = null
    var isFormalMenuClicked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_music_thai_pop)
        supportActionBar?.hide()
        init()
        back_musicpopthai!!.setOnClickListener {
            val intent = Intent(this, menu_music_thai::class.java)
            startActivity(intent)
        }
        popthai_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generatethaipop()
        }


    }
    fun generatethaipop() {
        // Clear the textview value to the default value
        popthai_result?.text = "xxxxxxxx"
        popthai_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            popthai_result,
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
            "สักวันหนึ่ง",
            "ความหมาย",
            "วันนี้",
            "เพื่อน",
            "กลับมาเหมือนเดิม",
            "เพียงใจ",
            "แสงสุดท้าย",
            "ฝัน",
            "รักเธอ",
            "คำสุดท้าย"
        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        popthai_result?.text = selectedClothes

        when (selectedClothes) {
            "สักวันหนึ่ง" -> popthai_info?.text =
                "ศิลปิน:ธีระ ชัยชนะ เป็นเพลงป๊อปที่เล่าเรื่องราวของความรักและความหวังในวันหนึ่ง"

            "ความหมาย" -> popthai_info?.text =
                "ศิลปิน:ปราณี ชาญโญพร เป็นเพลงป๊อปี่บอกถึงความหมายของความรักและความสัมพันธ์"

            "วันนี้" -> popthai_info?.text =
                "ศิลปิน:วิว อิงกูล เป็นเพลงป๊อปที่เล่าเรื่องราวของชีวิตประจำวันในวันนี้"

            "เพื่อน" -> popthai_info?.text =
                "ศิลปิน:ณัฐพล สุขเกษม เป็นเพลงป๊อปที่เล่าเรื่องราวของความเพื่อนและความสัมพันธ์"

            "กลับมาเหมือนเดิม" -> popthai_info?.text =
                "ศิลปิน:พรชัย อุดมเดชะ เป็นเพลงป๊อปที่บอกถึงการกลับมาของความรักและความหวัง"

            "เพียงใจ" -> popthai_info?.text =
                "ศิลปิน:ปรีชา แสงวงศ์สิน เป็นเพลงป๊อปที่เล่าเรื่องราวของความเชื่อมั่นและความหวัง"

            "แสงสุดท้าย" -> popthai_info?.text =
                "ศิลปิน:สุรชัย วงศ์วิชัย เป็นเพลงป๊อปที่เล่าเรื่องราวของความหวังและความสุข"

            "ฝัน" -> popthai_info?.text =
                "ศิลปิน:วชิรวิทย์ ภานุรักษ์ เป็นเพลงป๊อปี่บอกถึงความฝันและความหวังในชีวิต"

            "รักเธอ" -> popthai_info?.text =
                "ศิลปิน:นนท์ ศิริวัฒน์ เป็นเพลงป๊อปที่เล่าเรื่องราวของความรักและความหวัง"

            "คำสุดท้าย" -> popthai_info?.text =
                "ศิลปิน:ณัฐวุฒิ ศรีเกษมสุข เป็นเพลงป๊อปที่เล่าเรื่องราวของความหวังและการพักผ่อนในสิ่งสำคัญในชีวิต"

            else -> popthai_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_musicpopthai = findViewById(R.id.back_gen_popt)
        popthai_result = findViewById(R.id.thaipop_result)
        popthai_info = findViewById(R.id.thaipop_info)
        popthai_gen = findViewById(R.id.genthai_pop)
    }
}