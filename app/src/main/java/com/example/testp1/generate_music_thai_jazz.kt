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

class generate_music_thai_jazz : AppCompatActivity() {
    var back_musicjazzthai: ImageButton? = null
    var jazzthai_result: TextView? = null
    var jazzthai_info: TextView? = null
    var jazzthai_gen: Button? = null
    var isFormalMenuClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_music_thai_jazz)
        supportActionBar?.hide()
        init()
        back_musicjazzthai!!.setOnClickListener {
            val intent = Intent(this, menu_music_thai::class.java)
            startActivity(intent)
        }
        jazzthai_gen?.setOnClickListener {
            isFormalMenuClicked = true
            // Call the function to generate clothes
            generatethaijazz()
        }


    }

    fun generatethaijazz() {
        // Clear the textview value to the default value
        jazzthai_result?.text = "xxxxxxxx"
        jazzthai_info?.text = ""

        // Create ObjectAnimator for rotating around the x-axis
        val rotationXAnimator = ObjectAnimator.ofFloat(
            jazzthai_result,
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
            "ฟังแจ๊ส",
            "ดนตรีจากฟ้า",
            "ฝนสามัคคี",
            "หยุดโลก",
            "เที่ยวไปในคืนสว่าง",
            "เดินทางบนวัฒนธรรม",
            "รักนักดนตรี",
            "ความทรงจำ",
            "เสียงฝน",
            "วันของฉัน"
        )

        val randomIndex = Random.nextInt(allClothes.size)
        val selectedClothes = allClothes[randomIndex]
        jazzthai_result?.text = selectedClothes

        when (selectedClothes) {
            "ฟังแจ๊ส" -> jazzthai_info?.text =
                "ศิลปิน:พงษ์สิทธิ์ สุขสม เป็นเพลงแจ๊สที่มีทัศนคติเพลงแจ๊สและแสนสนุกสนาน"

            "ดนตรีจากฟ้า" -> jazzthai_info?.text =
                "ศิลปิน:จอมขวัญ ชีวรัตน์ เป็นเพลงแจ๊สที่สร้างความสุขและความสดใหม่ด้วยเสียงดนตรีอันสดใส"

            "ฝนสามัคคี" -> jazzthai_info?.text =
                "ศิลปิน:ภูมิพัฒน์ แสงวรรณ เป็นเพลงแจ๊สที่มีเสน่ห์และเป็นที่ติดตามของศิลปิน"

            "หยุดโลก" -> jazzthai_info?.text =
                "ศิลปิน:วีรวัฒน์ มูลเมือง เป็นเพลงแจ๊สที่สร้างความรู้สึกของความเงียบสงัดและความหมายที่ลึกซึ้ง"

            "เที่ยวไปในคืนสว่าง" -> jazzthai_info?.text =
                "ศิลปิน:มนัสวี ศรี เป็นเมืองเพลงแจ๊สที่สร้างความรู้สึกของการเดินทางในความมืด"

            "เดินทางบนวัฒนธรรม" -> jazzthai_info?.text =
                "ศิลปิน:นิว โซ เป็นเพลงแจ๊สที่มีสไตล์เอกลักษณ์และความหลงใหลในวัฒนธรรม"

            "รักนักดนตรี" -> jazzthai_info?.text =
                "ศิลปิน:มนต์สานต์ สุวรรณชนม์ เป็นเพลงแจ๊สที่สร้างความรักและความเข้าใจต่อนักดนตรี"

            "ความทรงจำ" -> jazzthai_info?.text =
                "ศิลปิน:กานต์ธีร์ สุพาณิชย์ เป็นเพลงแจ๊สที่มีความคิดสร้างสรรค์และความทรงจำที่ลึกซึ้ง"

            "เสียงฝน" -> jazzthai_info?.text =
                "ศิลปิน:โกโชธร ทองสุวรรณ เป็นเพลงแจ๊สที่มีสไตล์และความเป็นเอกลักษณ์"

            "วันของฉัน" -> jazzthai_info?.text =
                "ศิลปิน:อาร์ติวา วงศ์วิไล เป็นเพลงแจ๊สที่มีความอ่อนโยนและเสน่ห์ที่เป็นเอกลักษณ์"

            else -> jazzthai_info?.text = "ไม่มีข้อมูล"
        }
    }

    fun init() {
        back_musicjazzthai = findViewById(R.id.back_gen_jazzt)
        jazzthai_result = findViewById(R.id.thaijazz_result)
        jazzthai_info = findViewById(R.id.thaijazz_info)
        jazzthai_gen = findViewById(R.id.genthai_jazz)
    }
}