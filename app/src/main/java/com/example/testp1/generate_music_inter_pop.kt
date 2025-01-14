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

class generate_music_inter_pop : AppCompatActivity() {
    var back_musicpopinter: ImageButton? = null
    var popinter_result: TextView? = null
    var popinter_info: TextView? = null
    var popinter_gen: Button? = null
    var isFormalMenuClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_music_inter_pop)
        supportActionBar?.hide()
        init()
        back_musicpopinter!!.setOnClickListener {
            val intent = Intent(this, menu_music_nation::class.java)
            startActivity(intent)
        }
        popinter_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generateinterpop()
        }
    }
    fun generateinterpop() {
        // Clear the textview value to the default value
        popinter_result?.text = "xxxxxxxx"
        popinter_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            popinter_result,
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
            "As It Was",
            "Heat Waves",
            "Stay",
            "Shivers",
            "Levitating",
            "Bad Habits",
            "Good 4 U",
            "Montero (Call Me by Your Name)",
            "Peaches",
            "Industry Baby"
        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        popinter_result?.text = selectedClothes

        when (selectedClothes) {
            "As It Was" -> popinter_info?.text =
                "ศิลปิน:Harry Styles เป็นเพลงผสมผสานแนวเพลงป๊อปกับซินธ์ป๊อป เนื้อหาเพลงเกี่ยวกับความรักและความทรงจำ"

            "Heat Waves" -> popinter_info?.text =
                "ศิลปิน:Glass Animals เป็นเพลงป๊อปที่มีจังหวะช้า เนื้อหาเพลงเกี่ยวกับความรักที่สูญเสีย์"

            "Stay" -> popinter_info?.text =
                "ศิลปิน:The Kid Laroi & Justin Bieber เป็นเพลงป๊อปที่มีจังหวะช้า เนื้อหาเพลงเกี่ยวกับความรักที่ไม่สมหวัง"

            "Shivers" -> popinter_info?.text =
                "ศิลปิน:Ed Sheeran เป็นบทเพลงป๊อปโรแมนติก เนื้อหาเพลงเกี่ยวกับความรัก"

            "Levitating" -> popinter_info?.text =
                "ศิลปิน:Dua Lipa เป็นเพลงป๊อปที่มีจังหวะสนุกสนาน เนื้อหาเพลงเกี่ยวกับความรัก"

            "Bad Habits" -> popinter_info?.text =
                "ศิลปิน:Olivia Rodrigo เป็นเพลงป๊อปพังก์ เนื้อหาเพลงเกี่ยวกับการอกหัก"

            "Good 4 U" -> popinter_info?.text =
                "ศิลปิน:สุรชัย วงศ์วิชัย เป็นเพลงป๊อปที่เล่าเรื่องราวของความหวังและความสุข"

            "Montero (Call Me by Your Name)" -> popinter_info?.text =
                "ศิลปิน:Lil Nas X เป็นเพลงป๊อปแร็พ เนื้อหาเพลงเกี่ยวกับการยอมรับตัวเอง"

            "Peaches" -> popinter_info?.text =
                "ศิลปิน:Justin Bieber feat. Daniel Caesar & Giveon เป็นเพลงป๊อป R&B เนื้อหาเพลงเกี่ยวกับความรัก"

            "Industry Baby" -> popinter_info?.text =
                "ศิลปิน:Lil Nas X & Jack Harlow เป็นเพลงป๊อปแร็พ เนื้อหาเพลงเกี่ยวกับการต่อต้านค่านิยมในสังคม"

            else -> popinter_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_musicpopinter = findViewById(R.id.back_gen_popinter)
        popinter_result = findViewById(R.id.interpop_result)
        popinter_info = findViewById(R.id.interpop_info)
        popinter_gen = findViewById(R.id.geninter_pop)
    }
}