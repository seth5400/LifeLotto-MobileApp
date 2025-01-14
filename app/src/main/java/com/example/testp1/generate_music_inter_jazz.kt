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

class generate_music_inter_jazz : AppCompatActivity() {
    var back_musicjazzinter: ImageButton? = null
    var jazzinter_result: TextView? = null
    var jazzinter_info: TextView? = null
    var jazzinter_gen: Button? = null
    var isFormalMenuClicked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_music_inter_jazz)
        supportActionBar?.hide()
        init()
        back_musicjazzinter!!.setOnClickListener {
            val intent = Intent(this, menu_music_nation::class.java)
            startActivity(intent)
        }
        jazzinter_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generateinterjazz()
        }
    }

    fun generateinterjazz() {
        // Clear the textview value to the default value
        jazzinter_result?.text = "xxxxxxxx"
        jazzinter_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            jazzinter_result,
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
            "Just the Way You Are",
            "Uptown Funk",
            "All of Me",
            "Stay",
            "Run the World (Girls)",
            "Royals",
            "Sweater Weather",
            "Riptide",
            "Take Me to Church",
            "Thinking Out Loud"
        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        jazzinter_result?.text = selectedClothes

        when (selectedClothes) {
            "Just the Way You Are" -> jazzinter_info?.text =
                "ศิลปิน:Bruno Mars เป็นเพลงผสมผสานแนวเพลงป๊อปกับแจ๊ซได้อย่างลงตัว เนื้อหาเพลงโรแมนติก"

            "Uptown Funk" -> jazzinter_info?.text =
                "ศิลปิน:Mark Ronson ft. Bruno Mars เป็นเพลงแจ๊ซฟิวชั่นที่มีจังหวะสนุกสนาน"

            "All of Me" -> jazzinter_info?.text =
                "ศิลปิน:John Legend เป็นบทเพลงแจ๊ซโรแมนติก เหมาะสำหรับใช้ในงานแต่งงาน"

            "Stay" -> jazzinter_info?.text =
                "ศิลปิน:Rihanna ft. Mikky Ekko เป็นผสมผสานแนวเพลงป๊อปกับแจ๊ซ เนื้อหาเพลงเศร้า"

            "Run the World (Girls)" -> jazzinter_info?.text =
                "ศิลปิน:Beyoncé เป็นเพลงแจ๊ซที่มีจังหวะสนุกสนาน"

            "Royals" -> jazzinter_info?.text =
                "ศิลปิน:Lorde เป็นเพลงผสมผสานแนวเพลงป๊อปกับแจ๊ซ เนื้อหาเพลงเกี่ยวกับการต่อต้านสังคม"

            "Sweater Weather" -> jazzinter_info?.text =
                "ศิลปิน:The Neighbourhood เป็นเพลงแจ๊ซที่มีจังหวะช้า เนื้อหาเพลงเกี่ยวกับความรัก"

            "Riptide" -> jazzinter_info?.text =
                "ศิลปิน:Vance Joy เป็นเพลงแจ๊ซที่มีจังหวะช้า เนื้อหาเพลงเกี่ยวกับความรัก"

            "Take Me to Church" -> jazzinter_info?.text =
                "ศิลปิน:Hozier เป็นเพลงผสมผสานแนวเพลงป๊อปกับแจ๊ซ เนื้อหาเพลงเกี่ยวกับศาสนา"

            "Thinking Out Loud" -> jazzinter_info?.text =
                "ศิลปิน:Ed Sheeran เป็นเพลงบทเพลงแจ๊ซโรแมนติก เหมาะสำหรับใช้ในงานแต่งงาน"

            else -> jazzinter_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_musicjazzinter = findViewById(R.id.back_gen_jazzinter)
        jazzinter_result = findViewById(R.id.interjazz_result)
        jazzinter_info = findViewById(R.id.interjazz_info)
        jazzinter_gen = findViewById(R.id.geninter_jazz)
    }
}