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

class generate_music_thai_rock : AppCompatActivity() {
    var back_musicrockthai: ImageButton? = null
    var rockthai_result: TextView? = null
    var rockthai_info: TextView? = null
    var rockthai_gen: Button? = null
    var isFormalMenuClicked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_music_thai_rock)
        supportActionBar?.hide()
        init()
        back_musicrockthai!!.setOnClickListener {
            val intent = Intent(this, menu_music_thai::class.java)
            startActivity(intent)
        }
        rockthai_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generatethairock()

        }

    }

    fun generatethairock() {
        // Clear the textview value to the default value
        rockthai_result?.text = "xxxxxxxx"
        rockthai_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            rockthai_result,
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
            "เข้ามาเลย",
            "ศึกษานารี",
            "วัดใจ",
            "จี๊จ๊ะ",
            "โปรดส่งใครมารักฉันที",
            "ยาพิษ",
            "รุ้ง",
            "จันทร์เจ้า",
            "ซมซาน",
            "แสงสุดท้าย"
        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        rockthai_result?.text = selectedClothes

        when (selectedClothes) {
            "เข้ามาเลย" -> rockthai_info?.text =
                "ศิลปิน:Loso เป็นเพลงร็อคคลาสสิกที่มีเนื้อหาเกี่ยวกับการเชิญชวนให้ทุกคนมาสนุกกับดนตรี"

            "ศึกษานารี" -> rockthai_info?.text =
                "ศิลปิน:Labanoon เป็นเพลงร็อคที่มีเนื้อหาเกี่ยวกับการเสียดสีสังคม"

            "วัดใจ" -> rockthai_info?.text =
                "ศิลปิน:Silly Fools เป็นเพลงร็อคที่มีเนื้อหาเกี่ยวกับความรักที่ไม่สมหวัง"

            "จี๊จ๊ะ" -> rockthai_info?.text =
                "ศิลปิน:Silly Fools เพลงเพลงร็อคที่มีเนื้อหาเกี่ยวกับความสนุกสนาน"

            "โปรดส่งใครมารักฉันที" -> rockthai_info?.text =
                "ศิลปิน:Instinct เป็นเพลงร็อคที่มีเนื้อหาเกี่ยวกับความเหงา"

            "ยาพิษ" -> rockthai_info?.text =
                "ศิลปิน:Bodyslam เป็นเพลงร็อคที่มีเนื้อหาเกี่ยวกับการต่อสู้กับปัญหา"

            "รุ้ง" -> rockthai_info?.text =
                "ศิลปิน:Slot Machine เป็นเพลงร็อคที่มีเนื้อหาเกี่ยวกับความหวัง"

            "จันทร์เจ้า" -> rockthai_info?.text =
                "ศิลปิน:Slot Machine เป็นเพลงร็อคที่มีเนื้อหาเกี่ยวกับความรัก"

            "ซมซาน" -> rockthai_info?.text =
                "ศิลปิน:Loso เป็นเพลงร็อคที่มีเนื้อหาเกี่ยวกับความเสียใจ"

            "แสงสุดท้าย" -> rockthai_info?.text =
                "ศิลปิน:Bodyslam เป็นเพลงร็อคที่มีเนื้อหาเกี่ยวกับกำลังใจ"

            else -> rockthai_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_musicrockthai = findViewById(R.id.back_gen_rockt)
        rockthai_result = findViewById(R.id.thairock_result)
        rockthai_info = findViewById(R.id.thairock_info)
        rockthai_gen = findViewById(R.id.genthai_rock)
    }
}