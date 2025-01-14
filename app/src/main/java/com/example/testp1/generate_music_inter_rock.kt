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

class generate_music_inter_rock : AppCompatActivity() {
    var back_musicrockinter: ImageButton? = null
    var rockinter_result: TextView? = null
    var rocktinter_info: TextView? = null
    var rockinter_gen: Button? = null
    var isFormalMenuClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_music_inter_rock)
        supportActionBar?.hide()
        init()
        back_musicrockinter!!.setOnClickListener {
            val intent = Intent(this, menu_music_nation::class.java)
            startActivity(intent)
        }
        rockinter_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generateinterrock()

        }

    }

    fun generateinterrock() {
        // Clear the textview value to the default value
        rockinter_result?.text = "xxxxxxxx"
        rocktinter_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            rockinter_result,
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
            "abcdefu",
            "Enemy",
            "Seventeen Going Under",
            "The Adults Are Talking",
            "Chaise Longue",
            "My Name Is Dark",
            "Where Is My Mind?",
            "Smells Like Teen Spirit",
            "Creep",
            "Losing My Religion"
        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        rockinter_result?.text = selectedClothes

        when (selectedClothes) {
            "abcdefu" -> rocktinter_info?.text =
                "ศิลปิน:GAYLE เป็นเพลงป๊อปพังก์ เนื้อหาเพลงเกี่ยวกับความรักที่ไม่สมหวัง"

            "Enemy" -> rocktinter_info?.text =
                "ศิลปิน:Imagine Dragons & JID เป็นเพลงผสมผสานแนวเพลงป๊อปร็อคกับแร็พ เนื้อหาเพลงเกี่ยวกับการต่อสู้กับอุปสรรค"

            "Seventeen Going Under" -> rocktinter_info?.text =
                "ศิลปิน:Sam Fender เป็นเพลงอินดี้ร็อค เนื้อหาเพลงเกี่ยวกับความยากลำบากในวัยรุ่น"

            "The Adults Are Talking" -> rocktinter_info?.text =
                "ศิลปิน:The Strokes เป็นเพลงอินดี้ร็อค เนื้อหาเพลงเกี่ยวกับการวิพากษ์วิจารณ์สังคม"

            "Chaise Longue" -> rocktinter_info?.text =
                "ศิลปิน:Wet Leg เป็นเพลงอินดี้ร็อค เนื้อหาเพลงเกี่ยวกับความเบื่อหน่าย"

            "My Name Is Dark" -> rocktinter_info?.text =
                "ศิลปิน:IDLES เป็นเพลงโพสต์พังก์ เนื้อหาเพลงเกี่ยวกับความวิตกกังวล"

            "Where Is My Mind?" -> rocktinter_info?.text =
                "ศิลปิน:Pixies เป็นเพลงอัลเทอร์เนทีฟร็อค เนื้อหาเพลงเกี่ยวกับความสับสนในชีวิต"

            "Smells Like Teen Spirit" -> rocktinter_info?.text =
                "ศิลปิน:Nirvana เป็นเพลงกรันจ์ เนื้อหาเพลงเกี่ยวกับการต่อต้านสังคม"

            "Creep" -> rocktinter_info?.text =
                "ศิลปิน:Radiohead เป็นเพลงอัลเทอร์เนทีฟร็อค เนื้อหาเพลงเกี่ยวกับความรู้สึกด้อยค่า"

            "Losing My Religion" -> rocktinter_info?.text =
                "ศิลปิน:R.E.M. เป็นเพลงอัลเทอร์เนทีฟร็อค เนื้อหาเพลงเกี่ยวกับความสงสัยในศาสนา"

            else -> rocktinter_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_musicrockinter = findViewById(R.id.back_gen_rockinter)
        rockinter_result = findViewById(R.id.interrock_result)
        rocktinter_info = findViewById(R.id.interrock_info)
        rockinter_gen = findViewById(R.id.geninter_rock)
    }
}