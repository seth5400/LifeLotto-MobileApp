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

class generate_music_thai_rap : AppCompatActivity() {
    var back_musicrapthai: ImageButton? = null
    var rapthai_result: TextView? = null
    var rapthai_info: TextView? = null
    var rapthai_gen: Button? = null
    var isFormalMenuClicked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_music_thai_rap)
        supportActionBar?.hide()
        init()
        back_musicrapthai!!.setOnClickListener {
            val intent = Intent(this, menu_music_thai::class.java)
            startActivity(intent)
        }
        rapthai_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generatethairap()

        }
    }

    fun generatethairap() {
        // Clear the textview value to the default value
        rapthai_result?.text = "xxxxxxxx"
        rapthai_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            rapthai_result,
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
            "กูรักมึง",
            "เค้าก่อน",
            "ใจนักเลง",
            "พูดมาก",
            "นอนไม่หลับ",
            "กูไม่แร็ปแล้ว",
            "ไปด้วยกันนะ",
            "ทางผ่าน",
            "ลู่วิ่ง",
            "เศร้า"
        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        rapthai_result?.text = selectedClothes

        when (selectedClothes) {
            "กูรักมึง" -> rapthai_info?.text =
                "ศิลปิน:ILLSLICK เป็นเพลงรักแร็ปสุดโรแมนติกที่บอกเล่าเรื่องราวความรักของผู้ชายคนหนึ่งที่มีต่อผู้หญิงคนหนึ่ง"

            "เค้าก่อน" -> rapthai_info?.text =
                "ศิลปิน:Txrbo ft. MILLI เป็นเพลงแร็ปที่มีเนื้อหาเกี่ยวกับความรักที่ไม่สมหวัง"

            "ใจนักเลง" -> rapthai_info?.text =
                "ศิลปิน:Youngohm & TangBadVoice เป็นเพลงแร็ปที่มีเนื้อหาเกี่ยวกับความเป็นลูกผู้ชาย"

            "พูดมาก" -> rapthai_info?.text =
                "ศิลปิน:1MILLI เพลงแร็ปที่มีเนื้อหาเกี่ยวกับผู้หญิงที่มั่นใจในตัวเอง์"

            "นอนไม่หลับ" -> rapthai_info?.text =
                "ศิลปิน:Bell Warisara เป็นเพลงแร็ปที่มีเนื้อหาเกี่ยวกับความอกหัก"

            "กูไม่แร็ปแล้ว" -> rapthai_info?.text =
                "ศิลปิน:F.HERO ft. MILLI เป็นเพลงแร็ปที่มีเนื้อหาเกี่ยวกับการเสียดสีวงการเพลง"

            "ไปด้วยกันนะ" -> rapthai_info?.text =
                "ศิลปิน:Three Man Down เป็นเพลงแร็ปที่มีเนื้อหาเกี่ยวกับมิตรภาพ"

            "ทางผ่าน" -> rapthai_info?.text =
                "ศิลปิน:Dept เป็นเพลงแร็ปที่มีเนื้อหาเกี่ยวกับชีวิต"

            "ลู่วิ่ง" -> rapthai_info?.text =
                "ศิลปิน:OG Bobby ft. Billkin เป็นเพลงแร็ปที่มีเนื้อหาเกี่ยวกับการมุ่งมั่นทำตามความฝัน"

            "เศร้า" -> rapthai_info?.text =
                "ศิลปิน:SPRITE feat. GUYGEEGEE เป็นเพลงแร็ปที่มีเนื้อหาเกี่ยวกับความเศร้า"

            else -> rapthai_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_musicrapthai = findViewById(R.id.back_gen_rapt)
        rapthai_result = findViewById(R.id.thairap_result)
        rapthai_info = findViewById(R.id.thairap_info)
        rapthai_gen = findViewById(R.id.genthai_rap)
    }
}